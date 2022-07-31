package Game;

import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;

public class DynamicRigidbody extends Rigidbody{
    protected MathVector vel;


    // Constructors
    public DynamicRigidbody(){
        super();
        vel = new MathVector(0, 0);
    }

    public DynamicRigidbody(Dimension size){
        super(size);
        vel = new MathVector(0, 0);
    }

    public DynamicRigidbody(Dimension size, MathVector position) {
        super(size, position);
        this.vel = new MathVector(0, 0);
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


    protected void checkCollisions(ArrayList<Rigidbody> rbs){
        for (Rigidbody rb : rbs){
            for (Side side : Side.values()){
                if (side == Side.TOP & checkCollision(rb, side)){
                    this.setPos(new MathVector(this.pos.getX(), rb.getPos().getY()+rb.getSize().height));
                    this.setVel(new MathVector(this.vel.getX(), 0));
                } else if (side == Side.BOTTOM & checkCollision(rb, side)){
                    this.setPos(new MathVector(this.pos.getX(), rb.getPos().getY()-this.size.height));
                    this.setVel(new MathVector(this.vel.getX(), 0));
                    System.out.println(this.pos);
                } else if (side == Side.LEFT & this.checkCollision(rb, side)){
                    this.setPos(new MathVector(rb.getPos().getX()+rb.getSize().width, this.pos.getY()));
                    this.setVel(new MathVector(0, this.vel.getY()));
                } else if (side == Side.RIGHT & this.checkCollision(rb, side)) {
                    this.setPos(new MathVector(rb.getPos().getX(), this.pos.getY()));
                    this.setVel(new MathVector(0, this.vel.getY()));
                }
            }
        }
    }

    protected boolean checkCollision(Rigidbody rb, Side side){

        return switch (side) {
            case TOP ->
                    ((rb.pos.getY() + rb.size.getHeight() <= this.getPos().getY()) & withinBound(rb.pos.getX(), rb.size.width, this.pos.getX(), this.size.width));
            case BOTTOM ->
                    ((rb.pos.getY() <= this.getPos().getY() + this.size.height) & withinBound(rb.pos.getX(), rb.size.width, this.pos.getX(), this.size.width));
            case LEFT ->
                    ((rb.pos.getX() + rb.size.width < this.getPos().getX()) & withinBound(rb.pos.getY(), rb.size.height, this.pos.getY(), this.size.height));
            case RIGHT ->
                    ((rb.pos.getX() > this.getPos().getX() + this.size.width) & withinBound(rb.pos.getY(), rb.size.height, this.pos.getY(), this.size.height));
        };
    }


    // Checks if a line (a point and a length) would collide with another line
    // Works if lines going in same direction, ignores perpendicular axis
    private boolean withinBound(int pos1, int length1, int pos2, int length2){

        if (length1 > length2){
            return (pos1 <= pos2 & pos2 <= pos1+length1) || (pos1 <= pos2+length2 & pos2+length2 <= pos1+length1);
        }
        else{
            return (pos2 <= pos1 & pos1 <= pos2+length2) || (pos2 <= pos1+length1 & pos1+length1 <= pos2+length2);
        }
    }

    @Override
    public void render(Graphics g, MathVector offset, MathVector scale){
        Graphics2D g2d = setupGraphics2D(g);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.blue);

        MathVector origin = pos.sub(offset);

        Rectangle img = new Rectangle(origin.getX()*scale.getY(), origin.getY()*scale.getY(), size.width*scale.getY(), size.height*scale.getY());

        g2d.draw(img);
    }

    @Override
    public void update(int delay, ArrayList<Rigidbody> rbs){
        if (vel.getY() < 3){
            vel = vel.add(new MathVector(0, 1));
        }



//        System.out.println("Pos: "+pos.toString());
//        System.out.println("Vel: "+vel.toString());
//        System.out.println();

        checkCollisions(rbs);
        move(delay);

    }

    private void move(int delay) {
        pos = pos.add(vel.mult(delay/10));
    }


}
