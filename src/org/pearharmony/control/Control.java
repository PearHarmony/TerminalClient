package org.pearharmony.control;

import org.pearharmony.ui.TerminalMain;
import org.pearharmony.data.DataMain;

public class Control {

    TerminalMain ui;
    DataMain data;
    
    public Control() {
        ui = new TerminalMain(this);
        data = new DataMain();
        System.out.println("This is controling somthing!");

        ui.startConsoleInput();
        while (true) {
            System.out.println("Das ist ein test");
        }
    }

    public void test() {
        System.out.println("Das ist eib test");
    }
}
