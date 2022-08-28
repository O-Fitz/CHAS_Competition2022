package Application;

import Application.Levels.Level1;
import Application.Menus.MainMenu;
import Application.Menus.OptionsMenu;
import Application.Menus.PauseMenu;
import Physics.MathVector;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Application extends JFrame implements ActionListener {


    Renderer renderer;


    private Timer timer;
    private final int DELAY = 15;

    private Level level;
    private Level lastLevel;

    private PauseMenu pause;
    private MainMenu home;
    private OptionsMenu options;


    private GameState gamestate;
    private ArrayList<GameState> lastGamestates = new ArrayList<GameState>();

    final static int WIDTH = 1000;
    final static int HEIGHT = 750;



    public Application(){

        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        addMouseMotionListener(new MAdapter2());
        setFocusable(true);
        timer = new Timer(DELAY, this);
        timer.start();

        level = new Level1();
        home = new MainMenu();
        options = new OptionsMenu();
        pause = new PauseMenu();
        gamestate = GameState.PLAY;

        initUI();
    }

    private void initUI(){

        renderer = new Renderer(level, home, options, pause, gamestate);

        add(renderer);

        setSize(WIDTH, HEIGHT);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // Starts the program
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }

    // Is performed every DELAY ms
    // Mainloop (sort of)
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

    void changeLevel(int l){
        lastLevel = level;
        if (l == 1){
            level = new Level1();
        }
    }

    private void handleChangeEvent(ChangeEvent event){
        switch (event.type){
            case MENU_CHANGE -> {lastGamestates.add(gamestate); gamestate = event.menu;}
            case LEVEL_CHANGE -> {changeLevel(event.level); gamestate = GameState.PLAY;}
            case BACK -> {gamestate = lastGamestates.get(lastGamestates.size()-1); lastGamestates.remove(lastGamestates.size()-1);}
        }
    }

    // Handles inputs
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            ChangeEvent event = new ChangeEvent();
            switch (gamestate){
                case HOME -> {event = home.keyReleased(e);}
                case OPTIONS -> {event = options.keyReleased(e);}
                case PAUSE -> {event = pause.keyReleased(e);}
                case PLAY ->{event = level.keyReleased(e);}
            }

            handleChangeEvent(event);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ChangeEvent event = new ChangeEvent();
            switch (gamestate){
                case HOME -> {event = home.keyPressed(e);}
                case OPTIONS -> {event = options.keyPressed(e);}
                case PAUSE -> {event = pause.keyPressed(e);}
                case PLAY ->{event = level.keyPressed(e);}
            }
            handleChangeEvent(event);
        }

    }

    private class MAdapter extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.NONE;
            switch (gamestate){
                case HOME -> {}
                case OPTIONS -> {}
                case PAUSE -> {}
                case PLAY ->{}

            }
            handleChangeEvent(event);
        }

        @Override
        public void mousePressed(MouseEvent e){
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.NONE;
            switch (gamestate){
                case HOME -> {event = home.mousePressed(e);}
                case OPTIONS -> {event = options.mousePressed(e);}
                case PAUSE -> {event = pause.mousePressed(e);}
                case PLAY ->{}
            }
            handleChangeEvent(event);
        }

        @Override
        public void mouseReleased(MouseEvent e){
            ChangeEvent event = new ChangeEvent();
            event.type = ChangeEvent.eventType.NONE;
            switch (gamestate){
                case HOME -> {event = home.mouseReleased(e);}
                case OPTIONS -> {event = options.mouseReleased(e);}
                case PAUSE -> {event = pause.mouseReleased(e);}
                case PLAY ->{}
            }
            handleChangeEvent(event);
        }
    }

    private class MAdapter2 extends MouseMotionAdapter{
        @Override
        public void mouseMoved(MouseEvent e){
            ChangeEvent event = new ChangeEvent();
            switch (gamestate){
                case HOME -> {event = home.mouseMoved(e);}
                case OPTIONS -> {event = options.mouseMoved(e);}
                case PAUSE -> {event = pause.mouseMoved(e);}
                case PLAY -> {event.type = ChangeEvent.eventType.NONE;}
            }
            handleChangeEvent(event);
        }
    }

    public enum GameState{
        HOME,
        OPTIONS,
        PAUSE,
        PLAY
    }

    static MathVector transfromMousePos(Point pos, MathVector scale){
        return (new MathVector(pos.getX(), pos.getY()).add(new MathVector(-17.775*0.6, -17.775*1.5))).div(scale.getY());
    }
}
