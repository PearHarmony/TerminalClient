package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class Contact extends Command {
    private final String commandSyntax = "/contact <add:remove:edit> <name> (<ip>)";

    public Contact(TerminalMain ui, String[] subCommands) {
        super(ui, "contact");
        setSyntax(commandSyntax);

        this.subCommands = subCommands;
        decodeCommand();
    }

    @Override
    protected void decodeCommand() {
        if (subCommands.length > 1 && subCommands.length < 4) {
            switch (subCommands[1]) {
                case "add" -> subAdd();
                case "remove" -> subRemove();
                case "edit" -> subEdit();
                default -> errorSyntax();
            }
        } else { errorSyntax(); }
    }

    private void subEdit() {
        System.out.println("edit");
    }

    private void subAdd() {
        System.out.println("add");
    }

    private void subRemove() {
        System.out.println("remove");
    }
}
