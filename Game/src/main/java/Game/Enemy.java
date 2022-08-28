package Game;
import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity {

    private int shootCooldown = 0;
    
    public Enemy() {
        super();
    }

    public Enemy(int maxHealth){
        super(maxHealth);
    }

    public Enemy(Dimension size, int maxHealth) {
        super(size, maxHealth);
    }

    public Enemy(Dimension size, MathVector position, int maxHealth) {
        super(size, position, maxHealth);
    }

    public Enemy(Dimension size, MathVector position, MathVector velocity, int maxHealth) {
        super(size, position, velocity, maxHealth);
    }


    public void shoot(MathVector playerPos){
        if(shootCooldown < 1){
            shootCooldown = 200;
            
        }
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs){
        super.update(delay, rbs);

        if (shootCooldown > 0){
            shootCooldown--;
        }
    }


}

