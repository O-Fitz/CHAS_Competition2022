package Application.Levels;

import Game.Enemy;
import Game.Player;
import Game.Rigidbody;
import Physics.MathVector;

import java.awt.*;

public class Level1 extends Application.Level {

    @Override
    protected void initLevel(){
        int w = 28;
        int h = 5;
        Rigidbody rb = new Rigidbody(new Dimension(w, h), new MathVector(0.0, 10.0));
        rb.setCollisionAreaSize(new Dimension(w+1, h+1));
        rb.setCollisionAreaRelPos(new MathVector(-0.5, -0.5));
        addToRBS(rb);
        //rbs.add(new DynamicRigidbody(new Dimension(10, 10), new MathVector<Integer>(1, 2)));

        Player player = new Player(new Dimension(1, 1), new MathVector(0.0, 0.0), 20);
        setPlayer(player);

        Enemy enemy1 = new Enemy(new Dimension(1, 1), new MathVector(10.0, 0.0), 20);
        addEnemy(enemy1);
    }
}
