// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class ClearHistory extends Command {

    public ClearHistory(TerminalMain ui, String[] subCommands) {
        super(ui, "clear-history", "/clear-history", subCommands);
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length == 1 && subCommands[0].equals("/clear-history")) {
            ui.clearHistory();
        } else {
            errorSyntax();
        }
    }
}
