package org.pearharmony.control;

import org.pearharmony.ui.TerminalMain;

public class Control {

    TerminalMain ui;
    
    public Control() {
        ui = new TerminalMain(this);

        System.out.println("This is controling somthing!");

    }

    public void test() {
        System.out.println("Das ist eib test");
    }
}
