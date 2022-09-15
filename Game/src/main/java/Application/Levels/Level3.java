package Application.Levels;

import Application.CustomGUI.Text;
import Application.Level;
import Game.*;
import Physics.MathVector;

import java.awt.*;

public class Level3 extends Application.Level {

	@Override
	protected void initLevel(){

		// Platforms
		double w = 80;
		double h = 5;
		Rigidbody rb = new Rigidbody(new MathVector(w, h), new MathVector(0.0, 10.0)); // 1
		addToRBS(rb);
		rb = new Rigidbody(new MathVector(w, h), new MathVector(w+10, 10.0)); // 2
		addToRBS(rb);
        rb = new Rigidbody(new MathVector(0.1,0.1), new MathVector(40.5, 5.0)); // platform for enemy 1
        addToRBS(rb);
        rb = new Rigidbody(new MathVector(0.1,0.1), new MathVector(20.5, 5.0)); // platform for enemy 2
        addToRBS(rb);
        rb = new Rigidbody(new MathVector(0.1,0.1), new MathVector(130.5, 5.0)); // platform for enemy 3
        addToRBS(rb);
        rb.setCollide(true);

        Enemy enemy1 = new Enemy(new MathVector(1.0, 1.0), new MathVector(40.0, -5.0), 3);
		addEnemy(enemy1);
        Enemy enemy2 = new Enemy(new MathVector(1.0, 1.0), new MathVector(20.0, -5.0), 3);
		addEnemy(enemy2);
        Enemy enemy3 = new Enemy(new MathVector(1.0, 1.0), new MathVector(130.0, -5.0), 3);
		addEnemy(enemy3);

		// The player
		Player player = new Player(new MathVector(1.0, 1.0), new MathVector(0.0, 0.0), 4);
		setPlayer(player);


		// The endpole
		EndPole ep = new EndPole(new MathVector(1.0, 2.0), new MathVector(170.0, 8.0));
		addToRBS(ep);

		// Spike
		Spike spike = new Spike(new MathVector(1.0, 1.0), new MathVector(20.5, 9.0));
		addToRBS(spike);
        Spike spike2 = new Spike(new MathVector(1.0, 1.0), new MathVector(40.5, 9.0));
		addToRBS(spike2);
        Spike spike3 = new Spike(new MathVector(1.0, 1.0), new MathVector(130.5, 9.0));
		addToRBS(spike3);


		Dimension size = new Dimension(0, 15);
        MathVector pos = new MathVector(3.0, 4.0);
		Text text = new Text(pos, size, "^^^ This is your health (max 4) ^^^");
		text.setStatic(true);
		texts.add(text);

	}

	public Level3(int levelID){
		super(levelID);
	}

	public Level3(){
		super();
	}

	@Override
	public Level clone(int levelID){
		return new Level3(levelID);
	}

}
