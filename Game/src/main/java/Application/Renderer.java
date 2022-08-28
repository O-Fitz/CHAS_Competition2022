package Application;

import Application.Menus.MainMenu;
import Application.Menus.OptionsMenu;
import Application.Menus.PauseMenu;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    MainMenu home = new MainMenu();
    OptionsMenu options = new OptionsMenu();
    PauseMenu pauseMenu = new PauseMenu();

    Level level;

    Application.GameState gamestate;

    Renderer(Level l, Application.GameState ga){
        level = l;
        gamestate = ga;
    }

    // Called every frame to paint the screen
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        switch (gamestate){
            case HOME -> {}
            case OPTIONS -> {}
            case PAUSE -> {}
            case PLAY ->{

                level.renderLevel(g);}
        }

    }

    public void update(Level l, Application.GameState ga){
        level = l;
        gamestate = ga;
    }

}
