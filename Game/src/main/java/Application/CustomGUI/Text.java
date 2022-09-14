package Application.CustomGUI;

import Physics.MathVector;

import java.awt.*;

public class Text {

	private Point position;
	private Dimension size;

	private String text;
	private Dimension textBuffer;

	private Color textColor;

	private boolean isStatic = false;

	public Text(Point position, Dimension size, String text, Dimension textBuffer, Color textColor) {
		this.position = position;
		this.size = size;
		this.text = text;
		this.textBuffer = textBuffer;
		this.textColor = textColor;
	}

	public Text(Point position, Dimension size, String text) {
		this.position = position;
		this.size = size;
		this.text = text;
		this.textBuffer = new Dimension(0, 0);
		this.textColor = Color.BLACK;
	}

	public void render(Graphics2D g2d, MathVector scale){

		Point pos = new Point(getPosition());
		pos.x *= scale.getY();
		pos.y *= scale.getY();

		g2d.setColor(textColor);
		Font font = new Font("Dialog.plain", Font.PLAIN, size.height);
		g2d.setFont(font);
		pos.x += textBuffer.width*scale.getY();
		pos.y += font.getSize() + textBuffer.height*scale.getY();
		g2d.drawString(text, pos.x, pos.y+font.getSize()+textBuffer.height);
	}

	public void render(Graphics2D g2d, MathVector scale, MathVector offset){

		if (isStatic){
			Point pos = new Point(getPosition());
			pos.x *= scale.getY();
			pos.y *= scale.getY();

			g2d.setColor(textColor);
			Font font = new Font("Dialog.plain", Font.PLAIN, size.height);
			g2d.setFont(font);
			pos.x += textBuffer.width*scale.getY();
			pos.y += font.getSize() + textBuffer.height*scale.getY();
			g2d.drawString(text, pos.x, pos.y+font.getSize()+textBuffer.height);
		} else{
			Point pos = new Point(getPosition());

			pos.x -= (int)Math.round(offset.getX());
			pos.y -= (int)Math.round(offset.getY());

			pos.x *= scale.getY();
			pos.y *= scale.getY();

			g2d.setColor(textColor);
			Font font = new Font("Dialog.plain", Font.PLAIN, size.height);
			g2d.setFont(font);
			pos.x += textBuffer.width*scale.getY();
			pos.y += font.getSize() + textBuffer.height*scale.getY();
			g2d.drawString(text, pos.x, pos.y+font.getSize()+textBuffer.height);
		}
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

	public Dimension getTextBuffer() {
		return textBuffer;
	}

	public void setTextBuffer(Dimension textBuffer) {
		this.textBuffer = textBuffer;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean aStatic) {
		isStatic = aStatic;
	}
}
