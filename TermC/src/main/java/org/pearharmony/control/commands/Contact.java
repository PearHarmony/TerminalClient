// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class Contact extends Command {
    private final String commandSyntax = "/contact <add:remove:edit> <name> (<ip>)";

    public Contact(TerminalMain ui, String[] subCommands) {
        super(ui, "contact", subCommands);
        setSyntax(commandSyntax); // set general command syntax for syntax error

        decodeCommand();
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
    }

    private void subAdd() { // add contact list entry
        System.out.println("add");
    }

    private void subRemove() { // remove contact list entry
        System.out.println("remove");
    }
}
