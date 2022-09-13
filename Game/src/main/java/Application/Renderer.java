package Application;

import Application.Menus.LevelSelection;
import Application.Menus.MainMenu;
import Application.Menus.OptionsMenu;
import Application.Menus.PauseMenu;
import Physics.MathVector;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    MainMenu home;
    OptionsMenu options;
    PauseMenu pause;
    LevelSelection levelSelection;

    Level level;

    Application.GameState gamestate;

    double scaleBy = 40;
    MathVector scale = new MathVector(0.0, 0.0);

    Renderer(Level l, MainMenu h, OptionsMenu o, PauseMenu p, LevelSelection levelSelection, Application.GameState ga){
        super(null);

        level = l;
        home = h;
        options = o;
        pause = p;
        gamestate = ga;
        this.levelSelection = levelSelection;

    }

    private Graphics2D setupGraphics2D(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        return g2d;
    }

    // Called every frame to paint the screen
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = setupGraphics2D(g);
        Dimension screensize = getSize();
        scale = new MathVector(screensize.height/scaleBy, screensize.height/scaleBy);

        switch (gamestate){
            case HOME -> {home.render(g2d, scale);}
            case OPTIONS -> {options.render(g2d, scale);}
            case PAUSE -> {pause.render(g2d, scale);}
            case PLAY ->{level.renderLevel(g2d, screensize, scale, scaleBy);}
            case LEVEL_SELECTION -> {levelSelection.render(g2d, scale);}
        }

    }

    public void update(Level l, Application.GameState ga){
        level = l;
        gamestate = ga;
    }

}
