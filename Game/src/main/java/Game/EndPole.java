package Game;

import Physics.MathVector;

import java.awt.*;

public class EndPole extends Rigidbody{

	public EndPole() {
		super();
	}

	public EndPole(MathVector size, MathVector position) {
		super(size, position);
	}

	@Override
	public void render(Graphics2D g2d, MathVector offset, MathVector scale, Color... color) {


		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.green);

		MathVector origin = getPos().sub(offset);

		int x = (int) Math.round(origin.getX() * scale.getX());
		int y = (int) Math.round(origin.getY() * scale.getY());
		int w = (int) Math.round(getSize().getX() * scale.getX());
		int h = (int) Math.round(getSize().getY() * scale.getY());
		Rectangle img = new Rectangle(x, y, w, h);
		g2d.draw(img);
	}

	@Override
	protected DynamicRigidbody.CollisionEvent onPlayerCollision(){
		return DynamicRigidbody.CollisionEvent.FINISHED;
	}

}
