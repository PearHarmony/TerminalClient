// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;
import java.io.*;
import java.util.Scanner;

public class Contact extends Command {
    private final String contactsFilename = "contacts";

    public Contact(TerminalMain ui, String[] subCommands) {
        super(ui, "contact", "/contact <add:remove:edit> <name> (<ip>)", subCommands);
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length > 1 && subCommands.length < 4) {
            switch (subCommands[1]) { // possible subcommands
                case "add" -> subAdd();
                case "remove" -> subRemove();
                case "edit" -> subEdit();
                default -> errorSyntax();
            }
        } else { errorSyntax(); }
    }

    private void subEdit() { // edit contact list entry
        System.out.println("edit");
        try {
            File contacts = new File(contactsFilename);
            Scanner contactsRead = new Scanner(contacts);
            System.out.println(contactsRead.nextLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void subAdd() { // add contact list entry
        System.out.println("add");

        try {
            File contacts = new File(contactsFilename);
            FileWriter contactsWrite = new FileWriter(contacts);
            contactsWrite.write("sdasdasdasd");
            contactsWrite.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void subRemove() { // remove contact list entry
        System.out.println("remove");
    }
}