package Application.Levels;

import Application.CustomGUI.Text;
import Application.Level;
import Game.*;
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

		// Spike
		Spike spike = new Spike(new MathVector(1.0, 1.0), new MathVector(35.0, 9.0));
		addToRBS(spike);


		// Tutorial text
		MathVector pos = new MathVector(2.0, 3.0);
		Dimension size = new Dimension(0, 15);

		Text text = new Text(pos, size, "Move with WA or the arrows");
		texts.add(text);
		text = new Text(new MathVector(pos.getX(), pos.getY()+1), size, "Jump with space");
		texts.add(text);

		pos = new MathVector(3.0, 4.0);
		text = new Text(pos, size, "^^^ This is your health (max 4) ^^^");
		text.setStatic(true);
		texts.add(text);

		pos = new MathVector(11.0, 6.0);
		text = new Text(pos, size, "This is an enemy, they like to shoot at you");
		texts.add(text);
		text = new Text(new MathVector(pos.getX()+5, pos.getY()+1), new Dimension(0, 15), "v");
		texts.add(text);

		pos = new MathVector(32.0, 6.0);
		text = new Text(pos, size, "This is a spike, they hurt");
		texts.add(text);
		text = new Text(new MathVector(pos.getX()+3, pos.getY()+1), new Dimension(0, 15), "v");
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
		return new Level1(levelID);
	}

}
