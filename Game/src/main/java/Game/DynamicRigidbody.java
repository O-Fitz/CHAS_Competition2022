package Game;

import Physics.MathVector;

import java.awt.*;

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

    public DynamicRigidbody(Dimension size, MathVector vel) {
        super(size);
        this.vel = vel;
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


    @Override
    public void render(Graphics g){
        Graphics2D g2d = setupGraphics2D(g);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.blue);

        Rectangle img = new Rectangle(pos.getX(), pos.getY(), size.width, size.height);

        g2d.draw(img);
    }

    @Override
    public void update(int delay){
        move(delay);
    }

    private void move(int delay) {
        pos = pos.add(vel.mult(delay));
    }
}
