package Application.Levels;

import Application.CustomGUI.Text;
import Application.Level;
import Game.EndPole;
import Game.Enemy;
import Game.Player;
import Game.Rigidbody;
import Physics.MathVector;

import java.awt.*;

public class Level2 extends Application.Level {
    @Override
    protected void initLevel(){
        //Platforms:
        double w = 5;
        double h = 5;
        Rigidbody rb = new Rigidbody(new MathVector(w, h), new MathVector(0.0, 10.0)); // 1
		addToRBS(rb);
		rb = new Rigidbody(new MathVector(w, h), new MathVector(w+5.0, 10.0)); // 2
		addToRBS(rb);
		rb = new Rigidbody(new MathVector(w, h), new MathVector(w+15.0, 10.0)); // 3
        addToRBS(rb);
        rb = new Rigidbody(new MathVector(2.0, 2.0), new MathVector(w+25.0, 10.0)); // 4
        addToRBS(rb);
        rb = new Rigidbody(new MathVector(2.0, 2.0), new MathVector(w+32.0, 10.0)); // 5
		rb.setCollide(true);
		addToRBS(rb);


        Player player = new Player(new MathVector(1.0, 1.0), new MathVector(0.0, 0.0), 4);
		setPlayer(player);

        EndPole ep = new EndPole(new MathVector(1.0, 2.0), new MathVector(w+34, 8.0));
		addToRBS(ep);

        Dimension size = new Dimension(0, 15);
        MathVector pos = new MathVector(3.0, 4.0);
		Text text = new Text(pos, size, "^^^ This is your health (max 4) ^^^");
		text.setStatic(true);
		texts.add(text);
    }

    public Level2(int levelID){
        super(levelID);
    }

    public Level2(){
        super();
    }

    @Override
    public Level clone(int levelID){
        Level2 newLevel = new Level2(levelID);
        return newLevel;
    }
    
}