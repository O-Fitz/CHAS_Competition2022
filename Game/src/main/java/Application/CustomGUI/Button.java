package Application.CustomGUI;

import Physics.MathVector;

import java.awt.*;

public class Button <E>{

    private Point position;
    private Dimension size;

    private String text;
    private Dimension textBuffer;

    private Color color;
    private Color hoverColor;
    private boolean hover;

    private VoidFuctionCall<E> function;

    public Button(){

    }

    public Button(Dimension position, Dimension size, String text, VoidFuctionCall<E> fun) {
        this.position = new Point((int)position.getWidth(), (int)position.getHeight());
        this.size = size;
        this.text = text;
        this.function = fun;

        this.textBuffer = new Dimension(0, 0);
        this.color = new Color(1, 0, 0, 1);
        this.hoverColor = new Color(0, 1, 0, 1);
    }

    public Button(Point position, Dimension size, String text, VoidFuctionCall<E> fun) {
        this.position = position;
        this.size = size;
        this.text = text;
        this.function = fun;

        this.textBuffer = new Dimension(0, 0);
        this.color = new Color(255, 0, 0, 255);
        this.hoverColor = new Color(0, 255, 0, 255);
    }

    public void onPress(E ev){
        function.operation(ev);
    }

    public boolean isHoveredOver(Point mousePos){

        return (position.getX() <= mousePos.getX()) && (mousePos.getX() <= position.getX()+size.width)
            && (position.getY() <= mousePos.getY()) && (mousePos.getY() <= position.getY()+size.height);

    }

    public boolean isHoveredOver(MathVector mousePos){
        return (position.getX() <= mousePos.getX()) && (mousePos.getX() <= position.getX()+size.width)
            && (position.getY() <= mousePos.getY()) && (mousePos.getY() <= position.getY()+size.height);
    }

    public boolean isHoveredOver(Dimension mousePos){
        return (position.getX() <= mousePos.width) && (mousePos.width <= position.getX()+size.width)
                && (position.getY() <= mousePos.height) && (mousePos.height <= position.getY()+size.height);
    }


    // Getters and Setters
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Dimension getTextBuffer() {
        return textBuffer;
    }

    public void setTextBuffer(Dimension textBuffer) {
        this.textBuffer = textBuffer;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public Color getVisibleColor() {
        return (hover) ? hoverColor : color;
    }

}
