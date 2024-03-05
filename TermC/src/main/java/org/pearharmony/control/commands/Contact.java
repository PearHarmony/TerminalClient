// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;
import org.pearharmony.data.Contacts;

public class Contact extends Command {
    private Contacts contactBook;

    public Contact(TerminalMain ui, String[] subCommands) {
        super(ui, "contact", "/contact <add:remove:edit> <name> (<ip>)", subCommands);
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length > 1 && subCommands.length < 5) {
            contactBook = new Contacts();
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

    }

    private void subAdd() { // add contact list entry
        if (subCommands.length == 4) {
            if (contactBook.addContact(subCommands[2],subCommands[3]) == 0) {
                ui.displayText("Contact added - Name: " + subCommands[2] + " IP: " + subCommands[3]);
            } else { ui.displayError(3); }
        } else { errorSyntax(); }
    }

    private void subRemove() { // remove contact list entry
        System.out.println("remove");
    }
}