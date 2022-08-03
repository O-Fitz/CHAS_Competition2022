package Game;

import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


public class Rigidbody{

    private Dimension size;
    private MathVector pos;

    private MathVector collisionAreaPos;
    private Dimension collisionAreaSize;

    private boolean collide = true;

    // Constructors
    public Rigidbody(){
        size = new Dimension(1, 1);
        pos = new MathVector(0.0, 0.0);
        collisionAreaPos = pos;
        collisionAreaSize = size;
    }

    public Rigidbody(Dimension size){
        this.size = size;
        pos = new MathVector(0.0, 0.0);
        collisionAreaPos = pos;
        collisionAreaSize = size;
    }

    public Rigidbody(Dimension size, MathVector position){
        this.size = size;
        pos = position;
        collisionAreaPos = pos;
        collisionAreaSize = size;
    }


    // Setters and Getters
    public MathVector getPos() {
        return pos;
    }
    public void setPos(MathVector pos) {
        this.pos = pos;
    }
    public Dimension getSize() {
        return size;
    }
    public void setSize(Dimension size) {
        this.size = size;
    }
    public MathVector getCollisionAreaPos() {
        return collisionAreaPos;
    }
    public void setCollisionAreaPos(MathVector collisionAreaPos) {
        this.collisionAreaPos = collisionAreaPos;
    }
    public Dimension getCollisionAreaSize() {
        return collisionAreaSize;
    }
    public void setCollisionAreaSize(Dimension collisionAreaSize) {
        this.collisionAreaSize = collisionAreaSize;
    }
    public boolean isCollide() {
        return collide;
    }
    public void setCollide(boolean collide) {
        this.collide = collide;
    }

    protected Graphics2D setupGraphics2D(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        return g2d;
    }

    public void render(Graphics g, MathVector offset, MathVector scale){

        Graphics2D g2d = setupGraphics2D(g);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.red);

        MathVector origin = pos.sub(offset);

        int x = (int)Math.round(origin.getX()*scale.getY());
        int y = (int)Math.round(origin.getY()*scale.getY());
        int w = (int)Math.round(size.width*scale.getY());
        int h = (int)Math.round(size.height*scale.getY());
        Rectangle img = new Rectangle(x, y, w, h);

        g2d.draw(img);
    }

    public void update(int delay, ArrayList<Rigidbody> rbs){

    }

    enum Side{
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        NONE
    }

}
