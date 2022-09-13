package Application.Menus;

import Application.Application;
import Application.ChangeEvent;
import Application.CustomGUI.Button;
import Application.CustomGUI.FunctionCall;
import Application.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends Menu{

    @Override
    protected void setupUI(){
        // Level Selector
        Point pos = new Point(10, 10);
        Dimension size = new Dimension(10, 5);
        FunctionCall fun = ()->{
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.MENU_CHANGE;
            event.menu = Application.GameState.LEVEL_SELECTION;
            return event;
        };
        Button b = new Button(pos, size, "LEVELS", fun);
        buttons.add(b);

        // Options menu
        pos = new Point(10, 17);
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

        return event;
    }

    @Override
    public ChangeEvent keyReleased(KeyEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        return event;
    }


}
