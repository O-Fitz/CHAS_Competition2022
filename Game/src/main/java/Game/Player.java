package Game;
import Physics.MathVector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Player extends Entity{

    public Player() {
        super();
    }

    public Player(int maxHealth) {
        super(maxHealth);
    }

    public Player(Dimension size, int maxHealth) {
        super(size, maxHealth);
    }

    public Player(Dimension size, MathVector position, int maxHealth) {
        super(size, position, maxHealth);
    }

    public Player(Dimension size, MathVector position, MathVector velocity, int maxHealth) {
        super(size, position, velocity, maxHealth);
    }


    // handlePress and handleRelease
    // Returns true if input has been handled
    // Otherwise returns false
    public boolean handlePress(KeyEvent e){
        boolean handled = false;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            vel.setX(-1);
            handled = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            vel.setX(1);
            handled = true;
        }
        if (key == KeyEvent.VK_SPACE){
            jump();
            handled = true;
        }
        return handled;
    }

    public boolean handleRelease(KeyEvent e){
        boolean handled = false;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            vel.setX(0);
            handled = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            vel.setX(0);
            handled = true;
        }
        return handled;
    }

    private void jump(){

    }

}
