package Application.CustomGUI;

import Physics.MathVector;

import java.awt.*;

public class Star {
	private Point centrePos;
	private Dimension size;

	private Color color;

	double[]x = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	double[]y = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	public Star(Point position, Dimension size, Color color) {
		this.centrePos = position;
		this.size = size;
		this.color = color;
		setupPoints();
	}

	public Star(Point position, Dimension size) {
		this.centrePos = position;
		this.size = size;
		color = Color.BLACK;
		setupPoints();
	}

	private void setupPoints(){
		// Points on the star
		for (int i=0; i<=5; i++){
			x[2*i] = (Math.sin(Math.toRadians(72.0)*i));
			y[2*i] = (Math.cos(Math.toRadians(72.0)*i));
		}
		// Indents on the star
		for (int i =0; i<5; i++){
			x[2*i+1] = 0.5*Math.sin(Math.toRadians(72*i+36));
			y[2*i+1] = 0.5*Math.cos(Math.toRadians(72*i+36));
		}

		// Scaling
		for (int i = 0; i<11; i++){

			x[i] = (x[i]*size.width+centrePos.x);
			y[i] = (-y[i]*size.height+centrePos.y);
		}
	}

	public void render(Graphics2D g2d, MathVector scale){

		g2d.setColor(color);

		//int[]x={250,150,0,150,100,250,400,350,500,350};
		//int[]y={100,200,200,300,400,300,400,300,200,200};

		int[] drawx = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] drawy = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		for (int i=0; i<11; i++){
			drawx[i] = (int)Math.round(x[i]*scale.getX());
			drawy[i] = (int)Math.round(y[i]*scale.getY());
		}

		g2d.fillPolygon(drawx, drawy, 11);
	}

	public Point getPosition() {
		return centrePos;
	}

	public void setPosition(Point position) {
		this.centrePos = position;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
