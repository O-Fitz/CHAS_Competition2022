package Game;

import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


public class Rigidbody{

    private Dimension size;
    private MathVector pos;


    private MathVector collisionAreaRelPos;
    private Dimension collisionAreaSize;

    private boolean collide = true;

    // Constructors
    public Rigidbody(){
        size = new Dimension(1, 1);
        pos = new MathVector(0.0, 0.0);
        collisionAreaRelPos = new MathVector(0.0, 0.0);
        collisionAreaSize = size;
    }

    public Rigidbody(Dimension size){
        this.size = size;
        pos = new MathVector(0.0, 0.0);
        collisionAreaRelPos = new MathVector(0.0, 0.0);
        collisionAreaSize = size;
    }

    public Rigidbody(Dimension size, MathVector position){
        this.size = size;
        pos = position;
        collisionAreaRelPos = new MathVector(0.0, 0.0);
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
        return getPos().add(getCollisionAreaRelPos());
    }
    public void setCollisionAreaPos(MathVector collisionAreaPos) {
        this.collisionAreaRelPos = collisionAreaPos.sub(getPos());
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
    public MathVector getCollisionAreaRelPos() {
        return collisionAreaRelPos;
    }
    public void setCollisionAreaRelPos(MathVector collisionAreaRelPos) {
        this.collisionAreaRelPos = collisionAreaRelPos;
    }
    public MathVector getMidPos(){
        return getPos().mult(2.0).add(getSize()).mult(0.5);
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

    public void render(Graphics g, MathVector offset, MathVector scale, Color... color){

        Graphics2D g2d = setupGraphics2D(g);

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.red);

        MathVector origin = pos.sub(offset);

        int x = (int)Math.round(origin.getX()*scale.getX());
        int y = (int)Math.round(origin.getY()*scale.getY());
        int w = (int)Math.round(size.width*scale.getX());
        int h = (int)Math.round(size.height*scale.getY());
        Rectangle img = new Rectangle(x, y, w, h);
        g2d.draw(img);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.green);

        origin = getCollisionAreaPos().sub(offset);
        x = (int)Math.round(origin.getX()*scale.getX());
        y = (int)Math.round(origin.getY()*scale.getY());
        w = (int)Math.round(getCollisionAreaSize().width*scale.getX());
        h = (int)Math.round(getCollisionAreaSize().height*scale.getY());
        img = new Rectangle(x, y, w, h);
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
