package Game;

import Physics.MathVector;
import Physics.Ray;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class DynamicRigidbody extends Rigidbody{
	private MathVector vel;
	private int updateBuffer = 0;


	// Constructors
	public DynamicRigidbody(){
		super();
		vel = new MathVector(0.0, 0.0);
	}

	public DynamicRigidbody(MathVector size){
		super(size);
		vel = new MathVector(0.0, 0.0);
	}

	public DynamicRigidbody(MathVector size, MathVector position) {
		super(size, position);
		this.vel = new MathVector(0.0, 0.0);
	}

	public DynamicRigidbody(MathVector size, MathVector position, MathVector vel) {
		super(size, position);
		this.vel = vel;
	}

	// Setters and Getters
	public MathVector getVel() {
		return vel;
	}
	public void setVel(MathVector vel) {
		this.vel = vel;
	}
	public int getUpdateBuffer() {
		return updateBuffer;
	}
	public void setUpdateBuffer(int updateBuffer) {
		this.updateBuffer = updateBuffer;
	}

	protected void onCollision(Rigidbody rb){

	}


	protected void checkCollisions(ArrayList<Rigidbody> rbs, double time){

		for (Rigidbody rb : rbs){
			if (rb.isCollide()){
				Ray r = new Ray(this.getMidPos(), this.getVel());
				Side side = RayVsRect(rb, r);
				switch (side){
					case TOP -> {
						this.setPos(new MathVector(this.getPos().getX(), rb.getPos().getY() + rb.getSize().getX()));
						this.setVel(new MathVector(this.vel.getX(), Math.max(0, this.vel.getY())));
					}
					case BOTTOM -> {
						this.setPos(new MathVector(this.getPos().getX(), rb.getPos().getY()-this.getSize().getY()));
						this.setVel(new MathVector(this.getVel().getX(), Math.min(0, this.vel.getY())));
					}
					case LEFT -> {
						this.setPos(new MathVector(rb.getPos().getX()-this.getSize().getX(), this.getPos().getY()));
						this.setVel(new MathVector(0.0, this.vel.getY()));
					}
					case RIGHT  -> {
						this.setPos(new MathVector(rb.getPos().getX()+rb.getSize().getX(), this.getPos().getY()));
						this.setVel(new MathVector(0.0, this.vel.getY()));
					}
				}
				if (side != Side.NONE){
					onCollision(rb);
				}
			}
		}
	}

	protected static boolean pointInRect(Rigidbody rb, MathVector point){
		return (point.getX() >= rb.getCollisionAreaPos().getX()
				& point.getY() >= rb.getCollisionAreaPos().getY())

				&(point.getX() <= rb.getCollisionAreaPos().getX()+rb.getCollisionAreaSize().getX()
				&point.getY() <= rb.getCollisionAreaPos().getY()+rb.getCollisionAreaSize().getY());
	}

	protected static boolean pointInRect(Rigidbody rb, Dimension point){
		return (point.width >= rb.getCollisionAreaPos().getX()
				& point.height >= rb.getCollisionAreaPos().getY())

				&(point.width <= rb.getCollisionAreaPos().getX()+rb.getCollisionAreaSize().getX()
				&point.height <= rb.getCollisionAreaPos().getY()+rb.getCollisionAreaSize().getY());
	}

	protected static boolean rectInRect(Rigidbody rb1, Rigidbody rb2){
		return (rb1.getCollisionAreaPos().getX() < rb2.getCollisionAreaPos().getX() + rb2.getCollisionAreaSize().getX()
				& rb1.getCollisionAreaPos().getX() + rb1.getCollisionAreaSize().getX() > rb2.getCollisionAreaPos().getX())

				& (rb1.getCollisionAreaPos().getY() < rb2.getCollisionAreaPos().getY() + rb2.getCollisionAreaSize().getY()
				& rb1.getCollisionAreaPos().getY() + rb1.getCollisionAreaSize().getY() > rb2.getCollisionAreaPos().getY());
	}

	protected static boolean checkCollisionAABB(Rigidbody rb1, Rigidbody rb2){
		return rb1.getPos().getX() < rb2.getPos().getX()+rb2.getSize().getX()
				&rb1.getPos().getX() + rb1.getSize().getX() > rb2.getPos().getX()
				&rb1.getPos().getY() < rb2.getPos().getY()+rb2.getSize().getY()
				&rb1.getPos().getY() + rb1.getSize().getY() > rb2.getPos().getY();
	}

	protected static Side RayVsRect(Rigidbody rb, Ray ray){

		if (Objects.equals(ray.direction, new MathVector(0.0, 0.0))){
			return Side.NONE;
		}

		MathVector tNear = rb.getCollisionAreaPos().sub(ray.origin);
		tNear.setX(tNear.getX()/ray.direction.getX());
		tNear.setY(tNear.getY()/ray.direction.getY());

		MathVector tFar = rb.getCollisionAreaPos().add(rb.getCollisionAreaSize()).sub(ray.origin);
		tFar.setX(tFar.getX()/ray.direction.getX());
		tFar.setY(tFar.getY()/ray.direction.getY());

		// Changes NaN to appropriate infinty
		for (int i=0; i<tNear.size() ;  i++){
			if (tNear.get(i).isNaN()){
				if (ray.origin.get(i) >= rb.getCollisionAreaPos().get(i)) tNear.set(i, Double.NEGATIVE_INFINITY);
				else tNear.set(i, Double.POSITIVE_INFINITY);
			}
			if (tFar.get(i).isNaN()){
				if (ray.origin.get(i) >= rb.getCollisionAreaPos().get(i)) tFar.set(i, Double.NEGATIVE_INFINITY);
				else tFar.set(i, Double.POSITIVE_INFINITY);
			}
		}

		if (tNear.getX() > tFar.getX()) tNear.swap(tFar, 0);
		if (tNear.getY() > tFar.getY()) tNear.swap(tFar, 1);

		if (tNear.getX() > tFar.getY() || tNear.getY() > tFar.getX()) return Side.NONE;

		double tHitNear = Math.max(tNear.getX(), tNear.getY());
		double tHitFar = Math.min(tFar.getX(), tFar.getY());

		if (tHitFar < 0  || tHitNear > 1) return Side.NONE;

		if (tNear.getX() > tNear.getY()){
			if (ray.direction.getX() < 0) return Side.RIGHT;
			else return Side.LEFT;
		} else if (tNear.getX() < tNear.getY()){
			if (ray.direction.getY() < 0) return Side.TOP;
			else return Side.BOTTOM;
		}
		else{
			return Side.NONE;
		}

	}

	@Override
	public void render(Graphics2D g2d, MathVector offset, MathVector scale, Color... color){


		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(color[0]);

		MathVector origin = getPos().sub(offset);

		int x = (int)Math.round(origin.getX()*scale.getX());
		int y = (int)Math.round(origin.getY()*scale.getY());
		int w = (int)Math.round(getSize().getX()*scale.getX());
		int h = (int)Math.round(getSize().getY()*scale.getY());
		Rectangle img = new Rectangle(x, y, w, h);

		g2d.draw(img);
	}

	@Override
	public void update(int delay, ArrayList<Rigidbody> rbs) {

		double time = (double)delay/10;

		if (updateBuffer > 0) {
			updateBuffer -= 1;
		}
		//updateBuffer = 1;
		if (vel.getY() < 3) {
			vel = vel.add(new MathVector(0.0, 0.2));
		}

//        System.out.println("Pos: "+pos.toString());
//        System.out.println("Vel: "+vel.toString());
//        System.out.println();

		move(time);
		checkCollisions(rbs, time);



	}

	protected void move(double time) {
		setPos(getPos().add(vel.mult(time)));
		//setCollisionAreaPos(getCollisionAreaPos().add(vel.mult(time)));
	}

	public enum CollisionEvent{
		NONE,
		FINISHED,
		DEATH
	}

}
