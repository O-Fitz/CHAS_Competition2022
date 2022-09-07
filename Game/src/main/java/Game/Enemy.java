package Game;
import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity {

    private int shootCooldownReset = 200;
    private int shootCooldown = shootCooldownReset;

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


    public Projectile shoot(MathVector playerPos){
        if(shootCooldown == 0){
            MathVector vel = new MathVector(0.1,-0.1);
            MathVector pos = new MathVector(this.getPos().getX(),this.getPos().getY()).add(vel.normalise());

            Projectile shot = new Projectile(new Dimension(1,1), pos, vel, 1);
            shootCooldown = shootCooldownReset;
            return shot;
        }
        return null;
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs){
        super.update(delay, rbs);

        if (shootCooldown > 0){
            shootCooldown--;
        }
    }


}

