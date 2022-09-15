package Game;

import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;

public class Spike extends Rigidbody{

	private int maxDamageCooldown = 20;
	private int damageCooldown = 0;

	double[] x = {0, 0, 0};
	double[] y = {0, 0, 0};

	public Spike(MathVector size, MathVector position) {
		super(size, position);
		setCollide(false);
		setPoints();
	}

	public Spike() {
		super();
		setCollide(false);
		setPoints();
	}

	private void setPoints(){
		x[0] = getPos().getX();
		x[1] = getPos().getX()+getSize().getX()/2;
		x[2] = getPos().getX()+getSize().getX();

		y[0] = getPos().getY()+getSize().getY();
		y[1] = getPos().getY();
		y[2] = getPos().getY()+getSize().getY();
	}

	@Override
	public void update(int delay, ArrayList<Rigidbody> rbs){
		if (damageCooldown > 0){
			damageCooldown--;
		}
	}

	@Override
	public void render(Graphics2D g2d, MathVector offset, MathVector scale, Color... color){

		//super.render(g2d, offset, scale, color);
		g2d.setColor(Color.RED);

		int[] drawx = {0, 0, 0};
		int[] drawy = {0, 0, 0};

		for (int i=0; i<3; i++) {
			drawx[i] = (int) Math.round((x[i] - offset.getX())*scale.getX());
			drawy[i] = (int) Math.round((y[i] - offset.getY())*scale.getY());
		}

		g2d.drawPolygon(drawx, drawy, 3);

	}

	@Override
	protected DynamicRigidbody.CollisionEvent onPlayerCollision(){
		if (damageCooldown==0){
			damageCooldown = maxDamageCooldown;
			return DynamicRigidbody.CollisionEvent.DAMAGE;
		} else{
			return DynamicRigidbody.CollisionEvent.NONE;
		}
	}
}
