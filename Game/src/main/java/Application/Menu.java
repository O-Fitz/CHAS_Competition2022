package Application;

import Application.CustomGUI.Button;
import Application.Menus.PauseMenu;
import Physics.MathVector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class Menu {

    protected ArrayList<Button<Integer>> buttons;
    private Button buttonPressed;

    private MathVector scale = new MathVector(1.0, 1.0);

    public Menu(){
        buttons = new ArrayList<Button<Integer>>();
        setupUI();
    }

    protected void setupUI(){

    }

    public void render(Graphics2D g2d, MathVector scale){
        this.scale = scale;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        for (var button : buttons){
            Point pos = new Point(button.getPosition());
            pos.x *= scale.getY();
            pos.y *= scale.getY();

            Dimension size = new Dimension(button.getSize());
            size.width *= scale.getY();
            size.height *= scale.getY();

            Rectangle img = new Rectangle(pos, size);

            g2d.setStroke(new BasicStroke(1));

            g2d.setColor(button.getVisibleColor());

            g2d.draw(img);
        }
    }

    // Event Handlers

    public ChangeEvent keyPressed(KeyEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;

        return event;
    }

    public ChangeEvent keyReleased(KeyEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;
        return event;
    }

    public ChangeEvent mousePressed(MouseEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;

        Point pos = new Point(e.getPoint());

        for (var button : buttons){
            if (button.isHoveredOver(Application.transfromMousePos(pos, scale))){
                buttonPressed = button;
            }
        }

        return event;
    }

    public ChangeEvent mouseReleased(MouseEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;

        Point pos = new Point(e.getPoint());
        Button<Integer> buttonHovered = new Button<Integer>();
        boolean hov = false;

        for (var button : buttons){
            if (button.isHoveredOver(Application.transfromMousePos(pos, scale))){
                buttonHovered = button;
                hov = true;
            }
        }

        if (hov){
            if (buttonPressed.equals(buttonHovered)){
                buttonPressed.onPress(1);
            }
        }

        return event;
    }

    public ChangeEvent mouseMoved(MouseEvent e){
        ChangeEvent event = new ChangeEvent();
        event.type = ChangeEvent.eventType.NONE;

        Point pos = new Point(e.getPoint());

        for (var button : buttons){
            button.setHover(button.isHoveredOver(Application.transfromMousePos(pos, scale)));
        }

        return event;
    }
}
