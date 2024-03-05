// @C. Prickartz

/*
    This is a custom implementation of the Lenterna TextBox component. An add line method has been added, witch adds the
    new line to the top inset of the bottom. If the text box is completely filed this can cas a lot of lag for that reasons
    the line count has been artificially limited within the addLine function.
 */
package org.pearharmony.ui.lanternaFix;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.TextBox;

public class AutoScrollTextBox extends TextBox {
    public AutoScrollTextBox(TerminalSize preferredSize) {
        super(preferredSize);
    }

    @SuppressWarnings("UnusedReturnValue")
    public AutoScrollTextBox addLineAndAtTheTop(String line) {
        if (getLineCount() > 100) {
            do {
                removeLine(getLineCount() - 1);
            } while (getLineCount() > 100);
        }

        String text = getText();
        setText(line);
        addLine(text);
        return this;
    }
}