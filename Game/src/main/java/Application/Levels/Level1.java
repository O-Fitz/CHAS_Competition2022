package Application.Levels;

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
        double w = 28;
        double h = 5;
        Rigidbody rb = new Rigidbody(new MathVector(w, h), new MathVector(0.0, 10.0));
        rb.setCollisionAreaSize(new MathVector(w+1, h+1));
        rb.setCollisionAreaRelPos(new MathVector(-0.5, -0.5));
        addToRBS(rb);
        //rbs.add(new DynamicRigidbody(new Dimension(10, 10), new MathVector<Integer>(1, 2)));

        Player player = new Player(new MathVector(1.0, 1.0), new MathVector(0.0, 0.0), 4);
        setPlayer(player);

        Enemy enemy1 = new Enemy(new MathVector(1.0, 1.0), new MathVector(10.0, 0.0), 3);
        addEnemy(enemy1);

        EndPole ep = new EndPole(new MathVector(1.0, 2.0), new MathVector(5.0, 8.0));
        addToRBS(ep);

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
