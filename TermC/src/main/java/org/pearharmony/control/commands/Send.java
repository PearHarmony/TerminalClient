// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.data.messages.AudioMessage;
import org.pearharmony.data.messages.ImgMessage;
import org.pearharmony.ui.TerminalMain;
import java.io.File;

public class Send extends Command {

    public Send(TerminalMain ui, String[] subCommands) {
        super(ui, "send", "/send <img:audio> <recipient> <filepath[path can not contain spaces]>", subCommands);
    }

    @Override
    protected void decodeCommand() {
        if (subCommands.length > 1 && subCommands.length < 5) {
            switch (subCommands[1]) { // possible subcommands
                case "img" -> sendIMG();
                case "audio" -> sendAudio();
                default -> errorSyntax();
            }
        } else { errorSyntax(); }
    }

    private int checkPath() {
        // 0 -> success
        // 1 -> file not found
        File fileToCheck = new File(subCommands[3]);
        if (fileToCheck.exists() && fileToCheck.isFile()) { return 0; }
        else { return 1; }
    }

    private void sendIMG() {
        if (checkPath() == 0) {
            ImgMessage imgMsg = new ImgMessage(subCommands[2], subCommands[3]);
            imgMsg.Send();
            ui.fileSendImg(subCommands[3], subCommands[2]);
        } else { errorFileMissing(); }
    }

    private void sendAudio() {
        if (checkPath() == 0) {
            AudioMessage audioMessage = new AudioMessage(subCommands[2], subCommands[3]);
            audioMessage.Send();
            ui.fileSendAudio(subCommands[3], subCommands[2]);
        } else { errorFileMissing(); }
    }

    private void errorFileMissing() { ui.displayError(2); }
}
