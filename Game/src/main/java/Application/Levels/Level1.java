package Application.Levels;

import Application.CustomGUI.Text;
import Application.Level;
import Game.EndPole;
import Game.Enemy;
import Game.Player;
import Game.Rigidbody;
import Physics.MathVector;

import java.awt.*;

public class Level1 extends Application.Level {

	@Override
	protected void initLevel(){

		// Platforms
		double w = 28;
		double h = 5;
		Rigidbody rb = new Rigidbody(new MathVector(w, h), new MathVector(0.0, 10.0)); // 1
		addToRBS(rb);
		rb = new Rigidbody(new MathVector(w, h), new MathVector(w+3, 10.0)); // 2
		addToRBS(rb);
		rb = new Rigidbody(new MathVector(1.0, 2.0), new MathVector(10.0, 8.0));
		rb.setCollide(true);
		addToRBS(rb);

		// The player
		Player player = new Player(new MathVector(1.0, 1.0), new MathVector(0.0, 0.0), 4);
		setPlayer(player);

		// The enemy
		Enemy enemy1 = new Enemy(new MathVector(1.0, 1.0), new MathVector(15.0, 0.0), 3);
		addEnemy(enemy1);

		// The endpole
		EndPole ep = new EndPole(new MathVector(1.0, 2.0), new MathVector(50.0, 8.0));
		addToRBS(ep);

		// Tutorial text
		Point pos = new Point(3, 3);
		Dimension size = new Dimension(0, 15);

		Text text = new Text(pos, size, "^^^ This is your health (max 4) ^^^");
		text.setStatic(true);
		texts.add(text);

		pos = new Point(20, 20);
		text = new Text(pos, size, "This is an enemy, they like to shoot at you");
		texts.add(text);
		text = new Text(new Point(pos.x+5, pos.y+1), new Dimension(0, 15), "v");
		texts.add(text);

	}

	public Level1(int levelID){
		super(levelID);
	}

	public Level1(){
		super();
	}

	@Override
	public Level clone(int levelID){
		Level1 newLevel = new Level1(levelID);
		return newLevel;
	}

}
