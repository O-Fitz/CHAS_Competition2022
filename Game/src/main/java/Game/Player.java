package Game;
import Physics.MathVector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity{

    private int jumpCounter = 0;
    private int jumpCooldown = 0;

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
            setVel(new MathVector(getVel().getX()-0.6, getVel().getY()));
            handled = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            setVel(new MathVector(getVel().getX()+0.6, getVel().getY()));
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
            setVel(new MathVector(0.0, getVel().getY()));
            handled = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            setVel(new MathVector(0.0, getVel().getY()));
            handled = true;
        }
        return handled;
    }

    // Checks if the Player is able to jump
    private boolean canJump(){

        if (jumpCooldown != 0){
            return false; // Returns false if jumped to quickly after another jump
        }

        if (this.getVel().getY() == 0){ // Checks if
            jumpCounter = 0;
            return true;
        }else if (jumpCounter <= 1){
            return true;
        }
        return false;
    }

    private void jump(){
        if (canJump()) {
            setVel(getVel().add(new MathVector(0.0, -1.5)));
            jumpCounter++;
            jumpCooldown = 2;
        }
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs){
        super.update(delay, rbs);

        if (jumpCooldown > 0){
            jumpCooldown--;
        }
    }

}
