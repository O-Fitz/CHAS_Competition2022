package Game;
import Physics.MathVector;
import Physics.Ray;

import java.awt.*;
import java.util.ArrayList;


public class Projectile extends Entity{

    public boolean collided = false;
    
    public Projectile(Dimension size, MathVector position, MathVector velocity, int maxHealth) {
        super(size, position, velocity, maxHealth);
        
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs) {

        double time = (double)delay/10;

        move(time);
        checkCollisions(rbs, time);
        
    }
    @Override
    protected void checkCollisions(ArrayList<Rigidbody> rbs, double time){
        for (Rigidbody rb : rbs){
            if (rb.isCollide()){
                MathVector offset = new MathVector(0.0, 0.0).add(this.getCollisionAreaSize()).mult(0.5);
                Ray r = new Ray(this.getPos().add(offset), this.getVel());
                Side side = RayVsRect(rb, r);

                if (side != Side.NONE){
                    this.collided = true;
                    this.setVel(new MathVector(0.0,0.0));
                    rb.onProjectileCollision(this);
                }
            }
        }
    }
}