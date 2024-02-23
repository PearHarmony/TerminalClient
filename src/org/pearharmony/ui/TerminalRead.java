package org.pearharmony.ui;

import java.util.Scanner;

public class TerminalRead implements Runnable {

     Scanner console = new Scanner(System.in);

    @Override
    public void run() {
        while(true) {System.out.println(string());}
    }

    private String string(){
        return console.nextLine();
    }
}
