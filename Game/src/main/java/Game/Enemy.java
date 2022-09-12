package Game;
import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity {

    private int shootCooldownReset = 200;
    private int shootRange = 20;
    private int shootCooldown = shootCooldownReset;

    public Enemy() {
        super();
    }

    public Enemy(int maxHealth){
        super(maxHealth);
    }

    public Enemy(MathVector size, int maxHealth) {
        super(size, maxHealth);
    }

    public Enemy(MathVector size, MathVector position, int maxHealth) {
        super(size, position, maxHealth);
    }

    public Enemy(MathVector size, MathVector position, MathVector velocity, int maxHealth) {
        super(size, position, velocity, maxHealth);
    }


    public Projectile shoot(MathVector playerPos){
        MathVector difference = playerPos.sub(this.getPos());
        if(shootCooldown == 0 && difference.abs() <= shootRange){
            MathVector vel = difference.normalise().mult(0.15);
            //MathVector vel = new MathVector(0.1,-0.1);
            MathVector norm = vel.normalise();
            MathVector pos = getPos().add(vel.normalise().add(new MathVector(0.5, 0.0)));

            Projectile shot = new Projectile(new MathVector(0.5,0.5), pos, vel, shootRange, this.getPos());
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

