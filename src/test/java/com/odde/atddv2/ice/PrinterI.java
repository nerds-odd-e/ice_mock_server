package com.odde.atddv2.ice;

import com.zeroc.Ice.Current;

public class PrinterI implements Demo.Printer {
    @Override
    public void printString(String s, Current current) {
        System.out.println(s);
    }
}
