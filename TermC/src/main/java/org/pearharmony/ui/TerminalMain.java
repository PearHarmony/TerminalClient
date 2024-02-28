package org.pearharmony.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.pearharmony.control.Control;
import java.io.IOException;

public class TerminalMain {

    // Basig Terminal
    private Control ctrl;
    private DefaultTerminalFactory termFactory;
    private Terminal term;
    private TerminalScreen termScreen;
    private Panel contentPanel;
    private GridLayout grid;
    private Window window;
    private WindowBasedTextGUI gui;

    // GUI Content
    private TextBox msgBox;
    
    public TerminalMain(Control ctrl) {
        this.ctrl = ctrl;
        System.out.println("Das ist das terminal interface!");
        createTerminal();
    }

    private void createTerminal() {
        termFactory = new DefaultTerminalFactory();

        try {
            term = termFactory.createTerminal();

            termScreen = new TerminalScreen(term);
            termScreen.startScreen();

            gui = new MultiWindowTextGUI(termScreen);

            window = new BasicWindow("PearHarmony TerminalClient");

            contentPanel = new Panel(new GridLayout(2));

            grid = (GridLayout)contentPanel.getLayoutManager();
            grid.setHorizontalSpacing(3);

            window.setComponent(contentPanel);
            gui.addWindow(window);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initDefaultScreen();
    }

    private void initDefaultScreen() {
        msgBox = new TextBox(new TerminalSize(50,1)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.CENTER));
        msgBox.setReadOnly(false);
        Button btn = new Button("Send");


        contentPanel.addComponent(msgBox);
        contentPanel.addComponent(btn);

        Button.Listener btnLis = button -> {
            System.out.println(msgBox.getText());
            msgBox.setText("");
        };

        btn.addListener(btnLis);

        try {
            gui.updateScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gui.addWindowAndWait(window);
    }
}
