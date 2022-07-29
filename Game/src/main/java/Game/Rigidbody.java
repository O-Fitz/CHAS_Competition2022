package Game;

import Physics.MathVector;

import java.awt.*;


public class Rigidbody{

    protected Dimension size;
    protected MathVector pos;

    // Constructors
    public Rigidbody(){
        size = new Dimension(1, 1);
        pos = new MathVector(0, 0);
    }

    public Rigidbody(Dimension size){
        this.size = size;
        pos = new MathVector(0, 0);
    }

    public Rigidbody(Dimension size, MathVector position){
        this.size = size;
        pos = position;
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

    public void render(Graphics g){

        Graphics2D g2d = setupGraphics2D(g);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.red);

        Rectangle img = new Rectangle(pos.getX(), pos.getY(), size.width, size.height);

        g2d.draw(img);
    }

    public void update(int delay){

    }
}
