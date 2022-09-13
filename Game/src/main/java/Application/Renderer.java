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

	private boolean switching = false;
	private Application.GameState lastGameState;
	private Level lastLevel;

	private final int fadeTimerMax = 30;
	private int fadeTimer = 0;
	private int fadeScale = 1;
	private boolean fade = false;

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

		Application.GameState renderingGamestate = (!switching) ? (gamestate) : (lastGameState);
		switch (renderingGamestate){
			case HOME -> {home.render(g2d, scale);}
			case OPTIONS -> {options.render(g2d, scale);}
			case PAUSE -> {pause.render(g2d, scale);}
			case PLAY ->{level.renderLevel(g2d, screensize, scale, scaleBy);}
			case LEVEL_SELECTION -> {levelSelection.render(g2d, scale);}
		}


		if (fade){
			g2d.setColor(new Color(0, 0, 0, (int)((double)fadeTimer*255/fadeTimerMax)));
			Rectangle rect = new Rectangle(0, 0, screensize.width, screensize.height);
			g2d.fill(rect);

			fadeTimer += fadeScale;
			if (fadeTimer >= fadeTimerMax){
				fadeScale = -1;
				switching = false;
			}else if (fadeTimer == 0){
				fade = false;
				fadeScale = 1;
			}
		}

	}

	public void update(Level l, Application.GameState ga){
		if (!l.equals(level) || !(gamestate.equals(ga))){
			lastLevel = level;
			lastGameState = gamestate;

			level = l;
			gamestate = ga;
			fade = true;
			switching = true;
		}

	}

}
