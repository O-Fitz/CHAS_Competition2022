package Application.Menus;

import Application.Application;
import Application.ChangeEvent;
import Application.CustomGUI.Button;
import Application.CustomGUI.VoidFuctionCall;
import Application.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class PauseMenu extends Menu {

    public PauseMenu(){

    }

    @Override
    protected void setupUI(){
        Point pos = new Point(10, 10);
        Dimension size = new Dimension(10, 5);
        VoidFuctionCall<Integer> fun = (Integer x)->{System.out.println("TEST");};
        Button<Integer> b = new Button<Integer>(pos, size, "TEST INCREMENT", fun);

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
