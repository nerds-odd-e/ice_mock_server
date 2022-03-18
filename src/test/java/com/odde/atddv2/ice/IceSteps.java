package com.odde.atddv2.ice;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IceSteps {

    private String response;

    @Given("ice mock server")
    public void ice_mock_server() {
        new Thread(() -> {
            try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize()) {
                com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000");
                com.zeroc.Ice.Object object = new PrinterI();
                adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("SimplePrinter"));
                adapter.activate();
                communicator.waitForShutdown();
            }
        }).start();
    }

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

    @Then("ice client get server response")
    public void iceClientGetServerResponse() {
        System.out.println("response = " + response);
    }
}
