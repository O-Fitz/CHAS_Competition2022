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
public abstract class Level extends JPanel implements ActionListener{

    private ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
    private Player player;
    private Timer timer;
    private final int DELAY = 15;
    private State menu;



    double scaleBy = 40;
    MathVector offset = new MathVector(0.0, 0.0);
    MathVector scale = new MathVector(0.0, 0.0);

    public Level(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        timer = new Timer(DELAY, this);
        timer.start();
        menu = State.PLAY;

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
    private void renderLevel(Graphics g){
        Dimension screensize = getSize();
        scale = new MathVector(screensize.height/scaleBy, screensize.height/scaleBy);
        calculateOffset(screensize);

        for (Rigidbody rb : rbs) {
            rb.render(g, offset, scale);
        }

        player.render(g, offset, scale);
    }

    // Renders the pause menu
    // TODO: Create pause menu
    private void renderPause(Graphics g){}

    // Renders the options menu
    // TODO: Create options menu, should be accessible from pause menu and home screen
    private void renderOptions(Graphics g){}

    private void update(){
        player.update(timer.getDelay(), rbs);
    }


    // Called every frame to paint the screen
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        switch (menu){
            case PLAY->     renderLevel(g);
            case PAUSE->    renderPause(g);
            case OPTIONS->  renderOptions(g);
        }

    }

    // Is performed every DELAY ms
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (menu){
            case PLAY -> {
                update();
            }
            case PAUSE -> {}
            case OPTIONS -> {}
        }
        repaint();
    }

    // Handles inputs
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (menu) {
                case PLAY -> gameEventsPressed(e);
                case PAUSE -> pauseEventsPressed(e);
                case OPTIONS -> optionsEventsPressed(e);
            }
        }


        @Override
        public void keyPressed(KeyEvent e) {
            switch (menu) {
                case PLAY -> gameEventsReleased(e);
                case PAUSE -> pauseEventsReleased(e);
                case OPTIONS -> optionsEventsReleased(e);
            }
        }
    }

    // Handles inputs while playing
    private void gameEventsPressed(KeyEvent e) {
        if (!player.handleRelease(e)) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                menu = State.PAUSE;
            }
        }
    }

    private void gameEventsReleased(KeyEvent e){
        if (!player.handlePress(e)) {

        }
    }

    // Handles inputs while in pause menu
    private void pauseEventsPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            menu = State.PLAY;
        }
    }

    private void pauseEventsReleased(KeyEvent e){

    }

    // Handles inputs while in options menu
    private void optionsEventsPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            menu = State.PAUSE;
        }
    }

    private void optionsEventsReleased(KeyEvent e){

    }


    // Getters and setters


    public State getMenu() {
        return menu;
    }

    public void setMenu(State menu) {
        this.menu = menu;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addToRBS(Rigidbody rb){
        rbs.add(rb);
    }



    // State of the game:
    // GAME: playing the game
    // PAUSE: the pause menu
    // OPTIONS: the options menu
    protected enum State{
        PLAY,
        PAUSE,
        OPTIONS
    }
}
