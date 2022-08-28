package Application;

import Application.Levels.Level1;
import Application.Menus.MainMenu;
import Application.Menus.OptionsMenu;
import Application.Menus.PauseMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Application extends JFrame implements ActionListener {

    Renderer renderer;

    private Timer timer;
    private final int DELAY = 15;

    private Level level;
    private GameState gamestate;


    public Application(){

        addKeyListener(new TAdapter());
        setFocusable(true);
        timer = new Timer(DELAY, this);
        timer.start();

        level = new Level1();
        gamestate = GameState.PLAY;

        initUI();
    }

    private void initUI(){

        //add(new Level());

        setSize(1000, 750);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        renderer = new Renderer(level, gamestate);
        renderer.setVisible(true);
        add(renderer);

    }

    private void gameloop(){



    }

    // Starts the program
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
            ex.gameloop();
        });
    }

    // Is performed every DELAY ms
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gamestate){
            case HOME -> {}
            case OPTIONS -> {}
            case PAUSE -> {}
            case PLAY ->{level.update(timer.getDelay());}
        }
        renderer.update(level, gamestate);
        renderer.repaint();

    }

    // Handles inputs
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (gamestate){
                case HOME -> {}
                case OPTIONS -> {}
                case PAUSE -> {}
                case PLAY ->{level.gameEventsPressed(e);}
            }
        }


        @Override
        public void keyPressed(KeyEvent e) {
            switch (gamestate){
                case HOME -> {}
                case OPTIONS -> {}
                case PAUSE -> {}
                case PLAY ->{level.gameEventsReleased(e);}
            }
        }
    }

    protected enum GameState{
        HOME,
        OPTIONS,
        PAUSE,
        PLAY
    }
}
