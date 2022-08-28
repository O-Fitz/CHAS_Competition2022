package Application;

import Game.Player;
import Game.Rigidbody;
import Physics.MathVector;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

// An abstract class that all levels extend
// Allows all levels to have the same base functionality and have unique level design
public abstract class Level extends JPanel{

    private ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
    private Player player;


    MathVector offset = new MathVector(0.0, 0.0);

    public Level(){

        // Sets up level design
        initLevel();
    }

    // This method is a template method that every level overides
    // Sets up the level design, eg platforms, enemies etc
    protected void initLevel(){

    }

    void calculateOffset(Dimension screensize, double scaleBy){

        double buffer = 2.5;

        double scw = ((double)screensize.width/(scaleBy))*(2.0/3.0);

        if (player.getMidPos().getX() + offset.getX() - scw > buffer)
            offset.setX(player.getMidPos().getX()-buffer-scw);

        else if (player.getMidPos().getX() + offset.getX() - scw < -buffer)
            offset.setX(player.getMidPos().getX()+buffer-scw);

        offset.setY(player.getMidPos().getY()-(double)screensize.height/(scaleBy));

    }

    // Renders the level, all of the rigidbodies and the player
    public void renderLevel(Graphics2D g2d, Dimension screensize, MathVector scale, double scaleBy){

        calculateOffset(screensize, scaleBy);

        for (Rigidbody rb : rbs) {
            rb.render(g2d, offset, scale);
        }

        player.render(g2d, offset, scale);
    }

    public void update(int delay){
        player.update(delay, rbs);
    }


    // Handles inputs while playing
    public ChangeEvent keyPressed(KeyEvent e) {
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        if (player.handlePress(e)) {

        }
        else{
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE){
                event.type = ChangeEvent.eventType.MENU_CHANGE;
                event.menu = Application.GameState.PAUSE;
            }
        }
        return event;
    }

    public ChangeEvent keyReleased(KeyEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        if (player.handleRelease(e)) {

        }
        else{
            int key = e.getKeyCode();

        }
        return event;
    }

    public ChangeEvent mouseClicked(MouseEvent me){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        return event;
    }

    public ChangeEvent mouseReleased(MouseEvent me){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        return event;
    }


    // Getters and Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addToRBS(Rigidbody rb){
        rbs.add(rb);
    }

}
