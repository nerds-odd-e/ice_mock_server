package com.odde.atddv2.ice;

import Demo.Clock;
import Demo.ClockPrx;
import Demo.TimeOfDay;
import com.github.leeonky.jfactory.JFactory;
import com.odde.atddv2.ice.spec.TimeOfDays;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import static com.github.leeonky.dal.extension.assertj.DALAssert.expect;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CucumberConfiguration.class}, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
public class IceSteps {

    @Autowired
    JFactory jFactory;
    private String response;
    private TimeOfDay timeOfDayResponse;
    private Thread server;

    @When("ice client send request")
    public void iceClientSendRequest() {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize()) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10001");
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invalid proxy");
            }
            response = printer.printString("Hello world");
        }
    }

    @Given("ice mock server with response {string}")
    public void iceMockServerWithResponse(String response) {
        new Thread(() -> {
            try (Communicator communicator = Util.initialize()) {
                ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10001");
                PrinterI printerI = spy(new PrinterI());
                when(printerI.printString(anyString(), any(Current.class))).thenReturn(response);
                adapter.add(printerI, Util.stringToIdentity("SimplePrinter"));
                adapter.activate();
                communicator.waitForShutdown();
            }
        }).start();
    }

    @Then("ice client get server response {string}")
    public void iceClientGetServerResponse(String expected) {
        Assertions.assertThat(response).isEqualTo(expected);
    }

    @When("ice client send get time request")
    public void iceClientSendGetTimeRequest() {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize()) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("Clock:default -p 10000");
            ClockPrx clock = ClockPrx.checkedCast(base);
            if (clock == null) {
                throw new Error("Invalid proxy");
            }
            timeOfDayResponse = clock.getTime();
        }
    }

    @Then("ice client get server response structure")
    public void iceClientGetServerResponseStructure(String expression) {
        expect(timeOfDayResponse).should(expression);
    }

    @Given("ice mock server with for object {string}")
    public void iceMockServerWithForObject(String objectName) {
        new Thread(() -> {
            try (Communicator communicator = Util.initialize()) {
                ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints(objectName + "Adapter", "default -p 10000");
                Clock clock = spy(new ClockI());
                when(clock.getTime(any(Current.class))).thenReturn(jFactory.spec(TimeOfDays.TimeOfDay.class).query());
                adapter.add(clock, Util.stringToIdentity(objectName));
                adapter.activate();
                communicator.waitForShutdown();
            }
        }).start();
    }
}
