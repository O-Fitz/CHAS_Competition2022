package Game;
import Physics.MathVector;
import Physics.Ray;
import jdk.dynalink.linker.GuardingDynamicLinkerExporter;

import java.awt.*;
import java.util.ArrayList;


public class Projectile extends DynamicRigidbody {

    public boolean collided = false;
    private int range;
    private MathVector enemyPos;
    
    public Projectile(MathVector size, MathVector position, MathVector velocity, int range, MathVector enemyPos) {
        super(size, position, velocity);
        this.range = range;
        this.enemyPos = enemyPos;
        
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs) {

        double time = (double)delay/10;

        move(time);
        checkCollisions(rbs, time);
        if (this.getPos().sub(enemyPos).abs() > range){
            collided = true;
            this.setVel(new MathVector(0.0,0.0));
        }
        
    }
    @Override
    protected void checkCollisions(ArrayList<Rigidbody> rbs, double time){
        // Works, I'm going to leave it alone now
        // don't touch, it works, leave it at that.
        for (Rigidbody rb : rbs){
            if (rb.isCollide()){
                //MathVector offset = rb.getMidPos();
                Ray ray = new Ray(rb.getMidPos(), this.getVel().negative());
                Side side = RayVsRect(this, ray);

                if (side != Side.NONE){
                    this.collided = true;
                    this.setVel(new MathVector(0.0,0.0));
                    rb.onProjectileCollision(this);
                }
            }
        }
    }
}