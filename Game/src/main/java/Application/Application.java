package Application;

import Application.Levels.Level1;
import Application.Menus.MainMenu;
import Application.Menus.Options;
import com.sun.tools.javac.Main;

import java.awt.*;
import javax.swing.*;

public class Application extends JFrame{

    JPanel level1 = new Level1();
    JPanel home = new MainMenu();
    JPanel options = new Options();

    JPanel cards = new JPanel(new CardLayout());



    private Level level = new Level1();

    private GameState gamestate = GameState.PLAY;


    public Application(){
        initUI();
    }

    private void initUI(){

        //add(new Level());

        setSize(1000, 750);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cards.add(level1, GameState.PLAY.name());
        cards.add(home, GameState.HOME.name());
        cards.add(options, GameState.OPTIONS.name());

        add(cards);


    }

    private void gameloop(){

        switch (gamestate){
            case HOME -> {
                CardLayout c1 = (CardLayout) cards.getLayout();
                c1.show(cards,  gamestate.name());
            }
            case PLAY -> {
                CardLayout c1 = (CardLayout) cards.getLayout();
                c1.show(cards, gamestate.name());
                level.setMenu(Level.State.PLAY);
            }
            case OPTIONS -> {
                CardLayout c1 = (CardLayout) cards.getLayout();
                c1.show(cards,  gamestate.name());
            }
        }

    }

    // Starts the program
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
            ex.gameloop();
        });
    }

    private enum GameState{
        HOME,
        OPTIONS,
        PLAY
    }
}
