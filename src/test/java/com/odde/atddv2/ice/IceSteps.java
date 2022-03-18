package com.odde.atddv2.ice;

import com.zeroc.Ice.Object;
import com.zeroc.Ice.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class IceSteps {

    private String response;

    @When("ice client send request")
    public void iceClientSendRequest() {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize()) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
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
                ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000");
                PrinterI printerI = spy(new PrinterI());
                when(printerI.printString(anyString(), any(Current.class))).thenReturn(response);
                Object object = printerI;
                adapter.add(object, Util.stringToIdentity("SimplePrinter"));
                adapter.activate();
                communicator.waitForShutdown();
            }
        }).start();
    }

    @Then("ice client get server response {string}")
    public void iceClientGetServerResponse(String expected) {
        Assertions.assertThat(response).isEqualTo(expected);
    }
}
