// @C. Prickartz

package org.pearharmony.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.pearharmony.control.Control;
import org.pearharmony.ui.lanternaFix.AutoScrollTextBox;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TerminalMain {

    // Reference to control and runFunction
    private Control ctrl;
    private RunFunction runFunc;

    // global base blocks
    private Panel contentPanel;
    private Window window;
    private WindowBasedTextGUI gui;

    // global history -> possibly be edited out of context // using a custom implementation of TextBox
    private AutoScrollTextBox msgHistory;

    public TerminalMain(Control ctrl) {
        this.ctrl = ctrl;
        runFunc = new RunFunction(ctrl);
    }

    public void initUI() {
        createTerminal();
        initDefaultScreen();
    }

    private void createTerminal() { // create basig terminal Ui with Lanterna

        // Terminal creator
        DefaultTerminalFactory termFactory = new DefaultTerminalFactory();

        try {
            Terminal term = termFactory.createTerminal(); // base terminal

            // terminal ui screen
            TerminalScreen termScreen = new TerminalScreen(term);
            termScreen.startScreen();

            gui = new MultiWindowTextGUI(termScreen); // add multi windows ui to common terminal screen

            window = new BasicWindow("PearHarmony TerminalClient"); // create window for client

            // add window controller and layout option
            contentPanel = new Panel(new GridLayout(2));

            // crate layout manager for grid ui
            GridLayout grid = (GridLayout) contentPanel.getLayoutManager();
            grid.setHorizontalSpacing(3);

            window.setComponent(contentPanel); // add controllable panel to windows
            gui.addWindow(window); // add window to terminal ui

        } catch (IOException e) { // creation fall fallback IO-error
            throw new RuntimeException(e);
        }
    }

    private void initDefaultScreen() { // Init GUI Content
        TextBox msgBox = new TextBox( // massage box
                new TerminalSize(80, 1)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END,
                GridLayout.Alignment.CENTER));
        msgBox.setReadOnly(false); // allow user input for massage box

        // create button to send messages and commands
        Button sendBtn = new Button("Send");
        Button.Listener btnLis = button -> runFunc.pressSendButton(); // create button listener as lambda expressions
        sendBtn.addListener(btnLis); // bind listener

        msgHistory = new AutoScrollTextBox( // message history box
                new TerminalSize(80, 18));
        msgHistory.setReadOnly(true); // disable user input

        // add components to grid row 0
        contentPanel.addComponent(msgBox);
        contentPanel.addComponent(sendBtn);

        // add components to grid row 1 (spacer)
        contentPanel.addComponent(new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));

        // add components to grid row 2
        contentPanel.addComponent(msgHistory);
        contentPanel.addComponent(new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)));

        // regular timer for receiving msg's
        TimerTask taskTimer = new TimerTask() {
            @Override
            public void run() { runFunc.timerTick(); }
        };
        Timer timerUnit = new Timer();
        timerUnit.schedule(taskTimer, 2000, 2); // start after 2 sek every 2ms

        try { // update screen
            gui.updateScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // setup RunFunction instance with access to UI
        runFunc.setupGUIFunction(msgBox, msgHistory);

        // add window and make ui interactive
        gui.addWindowAndWait(window);

        // close client when window exit
        System.exit(0);
    }

    public void displayError(int errIndex) {
        String[] errorMSG = { // simple possible errors without arguments
                "generic error",
                "Command not found!"
        };
        runFunc.printTo_msgHistory("Error: " + errorMSG[errIndex]);
    }

    // display complex errors
    public void displayError(String error) { runFunc.printTo_msgHistory("Error: " + error); }

    // display arbitrary text to msg history
    public void displayText(String text) { runFunc.printTo_msgHistory("System Massage:" + text); }

    // clear message history
    public void clearHistory() { msgHistory.setText(""); }
}
