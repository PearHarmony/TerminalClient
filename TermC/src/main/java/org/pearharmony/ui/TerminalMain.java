package org.pearharmony.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.pearharmony.control.Control;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TerminalMain {

    // Referenz to control and runFunction
    private Control ctrl;
    private RunFunction runFunc;

    // Basig Terminal
    private DefaultTerminalFactory termFactory;
    private Terminal term;
    private TerminalScreen termScreen;
    private Panel contentPanel;
    private GridLayout grid;
    private Window window;
    private WindowBasedTextGUI gui;

    // GUI Content
    private TextBox msgBox;
    private Button sendBtn;
    private TextBox msgHistory;

    //
    private Timer timerUnit;

    public TerminalMain(Control ctrl) {
        this.ctrl = ctrl;
        runFunc = new RunFunction(ctrl);

        createTerminal();
        initDefaultScreen();
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

            grid = (GridLayout) contentPanel.getLayoutManager();
            grid.setHorizontalSpacing(3);

            window.setComponent(contentPanel);
            gui.addWindow(window);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initDefaultScreen() {
        msgBox = new TextBox(
                new TerminalSize(60, 1)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END,
                GridLayout.Alignment.CENTER));
        msgBox.setReadOnly(false);

        sendBtn = new Button("Send");
        Button.Listener btnLis = button -> runFunc.pressSendButton();
        sendBtn.addListener(btnLis);

        msgHistory = new TextBox(
                new TerminalSize(60, 18)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END,
                GridLayout.Alignment.CENTER));
        msgHistory.setReadOnly(true);

        // Grid row 0
        contentPanel.addComponent(msgHistory);
        contentPanel.addComponent(new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)));

        // Grid row 1
        contentPanel.addComponent(new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(3)));

        // Grid row 2
        contentPanel.addComponent(msgBox);
        contentPanel.addComponent(sendBtn);


        TimerTask taskTimer = new TimerTask() {
            @Override
            public void run() { runFunc.timerTick(); }
        };
        timerUnit = new Timer();
        timerUnit.schedule(taskTimer, 2000, 10);

        try {
            gui.updateScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        runFunc.setupGUIFunction(msgBox, msgHistory);

        gui.addWindowAndWait(window);
        System.exit(0);
    }
}
