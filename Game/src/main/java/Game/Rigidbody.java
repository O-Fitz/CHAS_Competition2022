package Game;

import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


public class Rigidbody{

    private MathVector size;
    private MathVector pos;


    private MathVector collisionAreaRelPos;
    private MathVector collisionAreaSize;

    private boolean collide = true;

    // Constructors
    public Rigidbody(){
        size = new MathVector(1.0, 1.0);
        pos = new MathVector(0.0, 0.0);
        collisionAreaRelPos = new MathVector(0.0, 0.0);
        collisionAreaSize = size;
    }

    public Rigidbody(MathVector size){
        this.size = size;
        pos = new MathVector(0.0, 0.0);
        collisionAreaRelPos = new MathVector(0.0, 0.0);
        collisionAreaSize = size;
    }

    public Rigidbody(MathVector size, MathVector position){
        this.size = size;
        pos = position;
        collisionAreaRelPos = new MathVector(-0.5, -0.5);
        collisionAreaSize = new MathVector(size.getX()+1, size.getY()+1);
    }


    // Setters and Getters
    public MathVector getPos() {
        return pos;
    }
    public void setPos(MathVector pos) {
        this.pos = pos;
    }
    public MathVector getSize() {
        return size;
    }
    public void setSize(MathVector size) {
        this.size = size;
    }
    public MathVector getCollisionAreaPos() {
        return getPos().add(getCollisionAreaRelPos());
    }
    public void setCollisionAreaPos(MathVector collisionAreaPos) {
        this.collisionAreaRelPos = collisionAreaPos.sub(getPos());
    }
    public MathVector getCollisionAreaSize() {
        return collisionAreaSize;
    }
    public void setCollisionAreaSize(MathVector collisionAreaSize) {
        this.collisionAreaSize = collisionAreaSize;
    }
    public boolean isCollide() {
        return collide;
    }
    public void setCollide(boolean collide) {
        this.collide = collide;
    }
    public MathVector getCollisionAreaRelPos() {
        return collisionAreaRelPos;
    }
    public void setCollisionAreaRelPos(MathVector collisionAreaRelPos) {
        this.collisionAreaRelPos = collisionAreaRelPos;
    }
    public MathVector getMidPos(){
        return getPos().mult(2.0).add(getSize()).mult(0.5);
    }

    // Render the rigidbody
    public void render(Graphics2D g2d, MathVector offset, MathVector scale, Color... color){


        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.red);

        MathVector origin = pos.sub(offset);

        int x = (int)Math.round(origin.getX()*scale.getX());
        int y = (int)Math.round(origin.getY()*scale.getY());
        int w = (int)Math.round(size.getX()*scale.getX());
        int h = (int)Math.round(size.getY()*scale.getY());
        Rectangle img = new Rectangle(x, y, w, h);
        g2d.draw(img);

    }

    // Called in level update
    public void update(int delay, ArrayList<Rigidbody> rbs){

    }

    // For when projectiles and the player collide with the rigidbody (overwritten in subclasses)
    protected void onProjectileCollision(Projectile proj){

    }

    protected DynamicRigidbody.CollisionEvent onPlayerCollision(){return DynamicRigidbody.CollisionEvent.NONE;}

    enum Side{
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        NONE
    }

}
