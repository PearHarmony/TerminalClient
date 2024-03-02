// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class Info extends Command {
    private final String commandSyntax = "/info";

    public Info(TerminalMain ui, String[] subCommand) {
        super(ui, "info", subCommand);
        setSyntax(commandSyntax); // set general command syntax for syntax error

        decodeCommand();
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length == 1 && subCommands[0].equals("/info")) {
            displayInfo();
        } else {
            errorSyntax();
        }
    }

    private void displayInfo() { // display program info
        ui.displayText("""
                
                -> PearHarmony TerminalClient\s
                -> made by https://pearharmony.aocalux.dev\s
                -> GitHub: https://github.com/pearharmony\s
                -> PearHarmony TerminalClient ist licensed under the GNU LESSER GENERAL PUBLIC LICENSE Version 2.1.\s
                -> The client is developed in Java using the Java JDK 17, Maven and the Terminal GUI library Lanterna.\s
                -> Maven: https://maven.apache.org/ \s
                -> Lanterna: https://github.com/mabe02/lanterna""");
    }
}
