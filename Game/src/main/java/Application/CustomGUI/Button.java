package Application.CustomGUI;

import Application.ChangeEvent;
import Physics.MathVector;

import java.awt.*;

public class Button {

    private Point position;
    private Dimension size;

    private String text;
    private Dimension textBuffer;

    private Color textColor;
    private Color hoverTextColor;
    private Color color;
    private Color hoverColor;
    private boolean hover;

    private FunctionCall function;

    public Button(){

    }

    public Button(Dimension position, Dimension size, String text, FunctionCall fun) {
        this.position = new Point((int)position.getWidth(), (int)position.getHeight());
        this.size = size;
        this.text = text;
        this.function = fun;

        this.textBuffer = new Dimension(0, 0);

        this.color = new Color(255, 0, 0);
        this.hoverColor = new Color(0, 255, 0);

        this.textColor = new Color(0, 0, 0);
        this.hoverTextColor = new Color(0, 0, 0);
    }

    public Button(Point position, Dimension size, String text, FunctionCall fun) {
        this.position = position;
        this.size = size;
        this.text = text;
        this.function = fun;

        this.textBuffer = new Dimension((size.width-text.length())/2, size.height/20);
        this.color = new Color(255, 0, 0, 255);
        this.hoverColor = new Color(0, 255, 0, 255);

        this.textColor = new Color(0, 0, 0);
        this.hoverTextColor = new Color(0, 0, 0);
    }

    public ChangeEvent onPress(){
        return function.op();
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

    public void render(Graphics2D g2d, MathVector scale){
        Point pos = new Point(getPosition());
        pos.x *= scale.getY();
        pos.y *= scale.getY();

        Dimension size = new Dimension(getSize());
        size.width *= scale.getY();
        size.height *= scale.getY();

        Rectangle rect = new Rectangle(pos, size);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(getVisibleColor());
        g2d.fill(rect);

        g2d.setColor(getVisibleTextColor());
        Font font = new Font("Dialog.plain", Font.PLAIN, size.height/4);
        g2d.setFont(font);
        pos.x += textBuffer.width*scale.getY();
        pos.y += font.getSize() + textBuffer.height*scale.getY();
        g2d.drawString(text, pos.x, pos.y+font.getSize()+textBuffer.height);

        //g2d.draw();
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

    public Color getVisibleTextColor(){
        //return (hover) ? hoverTextColor : textColor;
        return this.textColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getHoverTextColor() {
        return hoverTextColor;
    }

    public void setHoverTextColor(Color hoverTextColor) {
        this.hoverTextColor = hoverTextColor;
    }
}
