package com.odde.atddv2.ice;

import Demo.*;
import Ice.*;
import com.github.leeonky.jfactory.JFactory;
import com.odde.atddv2.ice.spec.TimeOfDays;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CucumberConfiguration.class}, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
public class IceSteps {

    private ObjectAdapter adapter;

    @When("ice client send clock printer request {string}")
    public void iceClientSendClockPrinterRequest(String arg0) {
        Communicator communicator = Util.initialize();
        ObjectPrx base = communicator.stringToProxy("ClockI:default -p 10000");
        ClockPrx clock = ClockPrxHelper.checkedCast(base);
        if (clock == null) {
            throw new Error("Invalid proxy");
        }
        clock.getPrinter().printString(arg0);
        communicator.destroy();
    }

    @Then("ice server receive printstring {string}")
    public void iceServerReceivePrintstring(String arg0) {
        verify(printerI).printString(eq(arg0), any());
    }

    public static class PrinterI extends Demo._PrinterDisp {
        @Override
        public String printString(String s, Current current) {
            return s + " - returned by code";
        }
    }

    public static class ClockI extends Demo._ClockDisp {

        @Override
        public TimeOfDay getTime(Current current) {
            return null;
        }

        @Override
        public void setTime(TimeOfDay time, Current current) {
        }

        @Override
        public PrinterPrx getPrinter(Current __current) {
            return null;
        }
    }

    @Autowired
    JFactory jFactory;
    private String response;
    private TimeOfDay timeOfDayResponse;
    private Communicator communicator = Util.initialize();
    private PrinterI printerI;
    private ClockI clockI;

    @Before
    public void startIceMockServer() {
        printerI = spy(new PrinterI());
        clockI = spy(new ClockI());
        adapter = communicator.createObjectAdapterWithEndpoints("MockServer", "default -p 10000");
        new Thread(() -> {
            adapter.add(printerI, Util.stringToIdentity("PrinterI"));
            adapter.add(clockI, Util.stringToIdentity("ClockI"));
            adapter.activate();
            communicator.waitForShutdown();
        }).start();

        PrinterPrx printerI1 = PrinterPrxHelper.uncheckedCast(adapter.createProxy(Util.stringToIdentity("PrinterI")));
        doReturn(printerI1).when(clockI).getPrinter(any());
    }

    @After
    public void stopIceMockServer() {
        communicator.shutdown();
    }

    @When("ice client send request")
    public void iceClientSendRequest() {
        Communicator communicator = Util.initialize();
        ObjectPrx base = communicator.stringToProxy("PrinterI:default -p 10000");
        Demo.PrinterPrx printer = Demo.PrinterPrxHelper.checkedCast(base);
        if (printer == null) {
            throw new Error("Invalid proxy");
        }
        response = printer.printString("Hello world");
        communicator.destroy();
    }

    @Given("ice mock server with response {string}")
    public void iceMockServerWithResponse(String response) {
        when(printerI.printString(anyString(), any(Current.class))).thenReturn(response);
    }

    @Then("ice client get server response {string}")
    public void iceClientGetServerResponse(String expected) {
        Assertions.assertThat(response).isEqualTo(expected);
    }

    @When("ice client send get time request")
    public void iceClientSendGetTimeRequest() {
        Communicator communicator = Util.initialize();
        ObjectPrx base = communicator.stringToProxy("ClockI:default -p 10000");
        ClockPrx clock = ClockPrxHelper.checkedCast(base);
        if (clock == null) {
            throw new Error("Invalid proxy");
        }
        timeOfDayResponse = clock.getTime();
        communicator.destroy();
    }

    @Then("ice client get server response structure")
    public void iceClientGetServerResponseStructure(String expression) {
        expect(timeOfDayResponse).should(expression);
    }

    @Given("ice mock server with for object {string}")
    public void iceMockServerWithForObject(String objectName) {
        when(clockI.getTime(any(Current.class))).thenReturn(jFactory.spec(TimeOfDays.TimeOfDay.class).query());
    }
}
