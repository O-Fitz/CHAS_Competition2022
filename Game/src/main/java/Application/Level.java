package Application;

import Game.DynamicRigidbody;
import Game.Player;
import Game.Rigidbody;
import Physics.MathVector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level extends JPanel implements ActionListener{

    ArrayList<Rigidbody> rbs;
    Player player;
    Timer timer;
    private final int DELAY = 15;
    MenuState menu;

    int scaleBy = 40;

    public Level(){
        initLevel();
    }

    void initLevel(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        timer = new Timer(DELAY, this);
        timer.start();

        rbs = new ArrayList<Rigidbody>();
        rbs.add(new Rigidbody(new Dimension(28, 5), new MathVector(0, 10)));
        //rbs.add(new DynamicRigidbody(new Dimension(10, 10), new MathVector<Integer>(1, 2)));

        player = new Player(new Dimension(1, 1), new MathVector(0, 0), 20);

        menu = MenuState.GAME;
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Dimension screensize = getSize();
        MathVector scale = new MathVector(screensize.width/scaleBy, screensize.height/scaleBy);
        MathVector offset = new MathVector(0, 0);


        switch (menu){
            case GAME:
                for (Rigidbody rb : rbs) {
                    rb.render(g, offset, scale);
                }
                player.render(g, offset, scale);
            case PAUSE:

            case OPTIONS:

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(timer.getDelay(), rbs);
        repaint();
    }


    private void drawDonut(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        int eh = 80;
        int ew = 80;

        Ellipse2D e = new Ellipse2D.Double(0, 0, ew, eh);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        AffineTransform at
                    = AffineTransform.getTranslateInstance((w-ew)/2, (h-eh)/2);

        g2d.draw(at.createTransformedShape(e));

//        for (double deg = 0; deg < 360; deg += 5) {
//            AffineTransform at
//                    = AffineTransform.getTranslateInstance(w/2, h/2);
//            at.rotate(Math.toRadians(deg));
//            g2d.draw(at.createTransformedShape(e));
//        }


    }


    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            if (!player.handleRelease(e)){

            }
        }
        @Override
        public void keyPressed(KeyEvent e){
            if(!player.handlePress(e)){

            }
        }
    }

    private enum MenuState{
        GAME,
        PAUSE,
        OPTIONS,
    }
}
