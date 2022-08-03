package Game;

import Physics.MathVector;
import Physics.Ray;

import java.awt.*;
import java.util.ArrayList;

public class DynamicRigidbody extends Rigidbody{
    private MathVector vel;
    private int updateBuffer = 0;


    // Constructors
    public DynamicRigidbody(){
        super();
        vel = new MathVector(0.0, 0.0);
    }

    public DynamicRigidbody(Dimension size){
        super(size);
        vel = new MathVector(0.0, 0.0);
    }

    public DynamicRigidbody(Dimension size, MathVector position) {
        super(size, position);
        this.vel = new MathVector(0.0, 0.0);
    }

    public DynamicRigidbody(Dimension size, MathVector position, MathVector vel) {
        super(size, position);
        this.vel = vel;
    }

    // Setters and Getters
    public MathVector getVel() {
        return vel;
    }
    public void setVel(MathVector vel) {
        this.vel = vel;
    }
    public int getUpdateBuffer() {
        return updateBuffer;
    }
    public void setUpdateBuffer(int updateBuffer) {
        this.updateBuffer = updateBuffer;
    }


    protected void checkCollisions(ArrayList<Rigidbody> rbs, double time){

        for (Rigidbody rb : rbs){
            if (rb.isCollide()){
                Ray r = new Ray(this.getPos(), this.getVel().mult(time));
                Side side = RayVsRect(rb, r);
                switch (side){
                    case TOP:
                        this.setPos(new MathVector(this.getPos().getX(), rb.getPos().getY()+rb.getSize().height));
                        this.setVel(new MathVector(this.vel.getX(), Math.max(0, this.vel.getY())));
                    case BOTTOM:
                        this.setPos(new MathVector(this.getPos().getX(), rb.getPos().getY()-this.getSize().height));
                        this.setVel(new MathVector(this.vel.getX(), Math.min(0, this.vel.getY())));
                    case LEFT:
                        this.setPos(new MathVector(rb.getPos().getX(), this.getPos().getY()));
                        this.setVel(new MathVector(0.0, this.vel.getY()));
                    case RIGHT:
                        this.setPos(new MathVector(rb.getPos().getX()+rb.getSize().width, this.getPos().getY()));
                        this.setVel(new MathVector(0.0, this.vel.getY()));
                }
            }
        }

    }

    protected static boolean pointInRect(Rigidbody rb, MathVector point){
        return (point.getX() >= rb.getCollisionAreaPos().getX()
                & point.getY() >= rb.getCollisionAreaPos().getY())
                
                &(point.getX() <= rb.getCollisionAreaPos().getX()+rb.getCollisionAreaSize().width
                &point.getY() <= rb.getCollisionAreaPos().getY()+rb.getCollisionAreaSize().height);
    }
    
    protected static boolean pointInRect(Rigidbody rb, Dimension point){
        return (point.width >= rb.getCollisionAreaPos().getX()
                & point.height >= rb.getCollisionAreaPos().getY())

                &(point.width <= rb.getCollisionAreaPos().getX()+rb.getCollisionAreaSize().width
                &point.height <= rb.getCollisionAreaPos().getY()+rb.getCollisionAreaSize().height);
    }

    protected static boolean rectInRect(Rigidbody rb1, Rigidbody rb2){
        return (rb1.getCollisionAreaPos().getX() < rb2.getCollisionAreaPos().getX() + rb2.getCollisionAreaSize().width
                & rb1.getCollisionAreaPos().getX() + rb1.getCollisionAreaSize().width > rb2.getCollisionAreaPos().getX())
                
                & (rb1.getCollisionAreaPos().getY() < rb2.getCollisionAreaPos().getY() + rb2.getCollisionAreaSize().height
                & rb1.getCollisionAreaPos().getY() + rb1.getCollisionAreaSize().height > rb2.getCollisionAreaPos().getY());
    }

    protected static boolean checkCollisionAABB(Rigidbody rb1, Rigidbody rb2){
        return rb1.getPos().getX() < rb2.getPos().getX()+rb2.getSize().width
                &rb1.getPos().getX() + rb1.getSize().width > rb2.getPos().getX()
                &rb1.getPos().getY() < rb2.getPos().getY()+rb2.getSize().height
                &rb1.getPos().getY() + rb1.getSize().height > rb2.getPos().getY();
    }

    protected static Side RayVsRect(Rigidbody rb, Ray ray){

        MathVector tNear = rb.getCollisionAreaPos().sub(ray.origin);
        tNear.setX(tNear.getX()/ray.direction.getX());
        tNear.setY(tNear.getY()/ray.direction.getY());

        MathVector tFar = rb.getCollisionAreaPos().add(rb.getCollisionAreaSize()).sub(ray.origin);
        tFar.setX(tFar.getX()/ray.direction.getX());
        tFar.setY(tFar.getY()/ray.direction.getY());

        if (ray.direction.getX() == 0 & rb.getCollisionAreaPos().getX() == 0){
            tNear.setX(Double.NEGATIVE_INFINITY);
            tFar.setX(Double.POSITIVE_INFINITY);
        }
        if (ray.direction.getY() == 0){
            tNear.setY(Double.POSITIVE_INFINITY);
            tFar.setY(Double.POSITIVE_INFINITY);
        }

        if (tNear.getX() > tFar.getX()) tNear.swap(tFar, 0);
        if (tNear.getY() > tFar.getY()) tNear.swap(tFar, 1);

        if (tNear.getX() > tFar.getY() || tNear.getY() > tFar.getX()) return Side.NONE;

        double tHitNear = Math.max(tNear.getX(), tFar.getY());
        double tHitFar = Math.max(tFar.getX(), tNear.getY());

        if (tHitFar < 0 || tHitNear > 1) return Side.NONE;

        if (tNear.getX() > tNear.getY()){
            if (ray.direction.getX() < 0) return Side.RIGHT;
            else return Side.LEFT;
        } else if (tNear.getX() < tNear.getY()){
            if (ray.direction.getY() < 0) return Side.BOTTOM;
            else return Side.TOP;
        }
        else{
            return Side.NONE;
        }

    }


    // Checks if a line (a point and a length) would collide with another line
    // Works if lines going in same direction, ignores perpendicular axis
    private boolean withinBound(double pos1, double length1, double pos2, double length2){

        if (length1 > length2){
            return (pos1 < pos2 & pos2 < pos1+length1) || (pos1 < pos2+length2 & pos2+length2 < pos1+length1);
        }
        else{
            return (pos2 < pos1 & pos1 < pos2+length2) || (pos2 < pos1+length1 & pos1+length1 < pos2+length2);
        }
    }

    @Override
    public void render(Graphics g, MathVector offset, MathVector scale){
        Graphics2D g2d = setupGraphics2D(g);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.blue);

        MathVector origin = getPos().sub(offset);

        int x = (int)Math.round(origin.getX()*scale.getY());
        int y = (int)Math.round(origin.getY()*scale.getY());
        int w = (int)Math.round(getSize().width*scale.getY());
        int h = (int)Math.round(getSize().height*scale.getY());
        Rectangle img = new Rectangle(x, y, w, h);

        g2d.draw(img);
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs) {

        double time = delay/10;

        if (updateBuffer > 0) {
            updateBuffer -= 1;
        }
        //updateBuffer = 1;
        if (vel.getY() < 3) {
            vel = vel.add(new MathVector(0.0, 0.2));
        }

//        System.out.println("Pos: "+pos.toString());
//        System.out.println("Vel: "+vel.toString());
//        System.out.println();

        move(time);
        checkCollisions(rbs, time);

    }

    private void move(double time) {
        setPos(getPos().add(vel.mult(time)));
    }

}
