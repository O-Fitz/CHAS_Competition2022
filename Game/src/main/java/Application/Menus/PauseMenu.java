package Application.Menus;

import Application.Application;
import Application.ChangeEvent;
import Application.CustomGUI.Button;
import Application.CustomGUI.FunctionCall;
import Application.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;


public class PauseMenu extends Menu {

    public PauseMenu(){

    }

    // Sets up the pause menu
    @Override
    protected void setupUI(){

        // Back button
        Point pos = new Point(10, 10);
        Dimension size = new Dimension(10, 5);
        FunctionCall fun = ()->{
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.BACK;
            return event;
        };
        Button b = new Button(pos, size, "BACK", fun);
        buttons.add(b);

        // Home button, takes you back to main menu
        pos = new Point(10, 17);
        size = new Dimension(10, 5);
        fun = ()->{
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.MENU_CHANGE;
            event.menu = Application.GameState.HOME;
            return event;
        };
        b = new Button(pos, size, "HOME", fun);
        buttons.add(b);

        // Options menu
        pos = new Point(10, 24);
        size = new Dimension(10, 5);
        fun = ()->{
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.MENU_CHANGE;
            event.menu = Application.GameState.OPTIONS;
            return event;
        };
        b = new Button(pos, size, "OPTIONS", fun);
        buttons.add(b);

    }


    @Override
    public ChangeEvent keyPressed(KeyEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE){
            event.type=ChangeEvent.eventType.BACK;
        }

        return event;
    }



}
