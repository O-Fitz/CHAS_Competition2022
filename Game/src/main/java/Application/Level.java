package Application;

import Game.Player;
import Game.Rigidbody;
import Physics.MathVector;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

// An abstract class that all levels extend
// Allows all levels to have the same base functionality and have unique level design
public abstract class Level extends JPanel{

    private ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
    private Player player;

    double scaleBy = 40;
    MathVector offset = new MathVector(0.0, 0.0);
    MathVector scale = new MathVector(0.0, 0.0);

    public Level(){

        // Sets up level design
        initLevel();
    }

    // This method is a template method that every level overides
    // Sets up the level design, eg platforms, enemies etc
    protected void initLevel(){

    }

    void calculateOffset(Dimension screensize){

        double buffer = 2.5;

        double scw = ((double)screensize.width/(scaleBy))*(2.0/3.0);

        if (player.getMidPos().getX() + offset.getX() - scw > buffer)
            offset.setX(player.getMidPos().getX()-buffer-scw);

        else if (player.getMidPos().getX() + offset.getX() - scw < -buffer)
            offset.setX(player.getMidPos().getX()+buffer-scw);

        offset.setY(player.getMidPos().getY()-(double)screensize.height/(scaleBy));

    }

    // Renders the level, all of the rigidbodies and the player
    public void renderLevel(Graphics g){
        Dimension screensize = getSize();
        scale = new MathVector(screensize.height/scaleBy, screensize.height/scaleBy);
        calculateOffset(screensize);

        for (Rigidbody rb : rbs) {
            rb.render(g, offset, scale);
        }

        player.render(g, offset, scale);
    }

    public void update(int delay){
        player.update(delay, rbs);
    }


    // Handles inputs while playing
    public void gameEventsPressed(KeyEvent e) {
        if (!player.handleRelease(e)) {

        }
    }

    public void gameEventsReleased(KeyEvent e){
        if (!player.handlePress(e)) {

        }
    }

    // Handles inputs while in pause menu

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
