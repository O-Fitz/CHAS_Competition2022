package Application.Menus;

import Application.ChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class MainMenu extends Application.Menu{

    @Override
    protected void setupUI(){

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
