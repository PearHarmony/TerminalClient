// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.data.Contacts;
import org.pearharmony.ui.TerminalMain;

public class Contact extends Command {
    private Contacts contactBook;

    public Contact(TerminalMain ui, String[] subCommands) {
        super(ui, "contact", "/contact <add:remove:edit> <name> (<ip>)", subCommands);
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length > 1 && subCommands.length < 5) {
            contactBook = new Contacts();
            switch (subCommands[1].toLowerCase()) { // possible subcommands
                case "add", "+" -> subAdd();
                case "remove", "del", "-" -> subRemove();
                case "edit", "e" -> subEdit();
                default -> errorSyntax();
            }
        } else {
            errorSyntax();
        }
    }

    private void subEdit() { // edit contact list entry
        if (subCommands.length == 4) {
            if (subCommands[2].equalsIgnoreCase(subCommands[3])) {
                ui.displayError(4);
            } else if (contactBook.removeContact(subCommands[2].toLowerCase()) == 0) {
                if (contactBook.addContact(subCommands[2].toLowerCase(), subCommands[3].toLowerCase()) == 0) {
                    ui.displayText(" Contact " + subCommands[2].toLowerCase() + " has been changed");
                } else {
                    ui.displayError(6);
                }
            } else {
                ui.displayError(6);
            }
        } else {
            errorSyntax();
        }
    }

    private void subAdd() { // add contact list entry
        if (subCommands.length == 4) {
            if (subCommands[2].equalsIgnoreCase(subCommands[3])) {
                ui.displayError(4);
            } else if (contactBook.addContact(subCommands[2].toLowerCase(), subCommands[3].toLowerCase()) == 0) {
                ui.displayText(" Contact added - Name: " + subCommands[2].toLowerCase() + " IP: " + subCommands[3].toLowerCase());
            } else {
                ui.displayError(3);
            }
        } else {
            errorSyntax();
        }
    }

    private void subRemove() { // remove contact list entry
        if (subCommands.length == 3) {
            if (contactBook.removeContact(subCommands[2].toLowerCase()) == 0) {
                ui.displayText(" The contact " + subCommands[2].toLowerCase() + " has been deleted successfully.");
            } else {
                ui.displayError(5);
            }
        } else {
            errorSyntax();
        }
    }
}