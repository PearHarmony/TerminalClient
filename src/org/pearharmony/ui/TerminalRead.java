package org.pearharmony.ui;

import java.util.Scanner;

public class TerminalRead {

     Scanner console = new Scanner(System.in);

    public String string(){
        return console.nextLine();
    }
}
