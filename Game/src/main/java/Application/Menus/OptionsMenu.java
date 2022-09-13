package Application.Menus;

import Application.ChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class OptionsMenu extends Application.Menu {

    @Override
    protected void setupUI(){

    }


    // Event Handlers
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


    @Override
    public ChangeEvent keyReleased(KeyEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        event = super.keyReleased(e);
        if (event.type != ChangeEvent.eventType.NONE){}
        return event;
    }




}
