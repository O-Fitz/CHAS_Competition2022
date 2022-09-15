package Game;
import Physics.MathVector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity{

	private int jumpCounter = 0;
	private int jumpCooldown = 0;

	private int playerShotCd = 0;

	private final double speed = 0.4;

	private boolean completedLevel = false;

	public Player() {
		super();
	}

	public Player(int maxHealth) {
		super(maxHealth);
	}

	public Player(MathVector size, int maxHealth) {
		super(size, maxHealth);
	}

	public Player(MathVector size, MathVector position, int maxHealth) {
		super(size, position, maxHealth);
	}

	public Player(MathVector size, MathVector position, MathVector velocity, int maxHealth) {
		super(size, position, velocity, maxHealth);
	}


	//TODO: Player attacking the enemy

	// handlePress and handleRelease
	// Returns true if input has been handled
	// Otherwise returns false
	public boolean handlePress(KeyEvent e){
		boolean handled = false;
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
			setVel(new MathVector(getVel().getX()-speed, getVel().getY()));
			handled = true;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
			setVel(new MathVector(getVel().getX()+speed, getVel().getY()));
			handled = true;
		}
		if (key == KeyEvent.VK_SPACE){
			jump();
			handled = true;
		}
		if (key == KeyEvent.VK_Q){
			// shoot shotgun left]
			// if(playerShotCd <= 0){
			// 	MathVector pos = this.getPos();
			// 	Projectile playerShot = new Projectile(new MathVector(0.5,0.5), pos, new MathVector(10.0,0.0), 10, this.getPos());
			// 	playerShotCd = 20;
			// }
			handled = true;
		}
		if (key == KeyEvent.VK_E){
			// shoot shotgun right
			handled = true;
		}
		return handled;
	}

	public boolean handleRelease(KeyEvent e){
		boolean handled = false;
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
			setVel(new MathVector(0.0, getVel().getY()));
			handled = true;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
			setVel(new MathVector(0.0, getVel().getY()));
			handled = true;
		}
		if (key == KeyEvent.VK_Q){
			// shotgun left key released
			handled = true;
		}
		if (key == KeyEvent.VK_E){
			// shotgun right released
			handled = true;
		}
		return handled;
	}

	@Override
	protected void onCollision(Rigidbody rb){
		CollisionEvent ev = rb.onPlayerCollision();

		switch (ev){
			case FINISHED -> {completedLevel = true;}
			case DEATH -> {health = 0;}
			case DAMAGE -> {health -= 1;}
		}
	}


	// Checks if the Player is able to jump
	private boolean canJump(){

		if (jumpCooldown != 0){
			return false; // Returns false if jumped to quickly after another jump
		}

		if (this.getVel().getY() == 0){ // Checks if
			jumpCounter = 0;
			return true;
		}else if (jumpCounter <= 1){
			return true;
		}
		return false;
	}

	private void jump() {
		if (canJump()) {
			setVel(getVel().add(new MathVector(0.0, -0.9)));
			jumpCounter++;
			jumpCooldown = 2;
		}
	}

	@Override
	public void update(int delay, ArrayList<Rigidbody> rbs){
		super.update(delay, rbs);

		if (jumpCooldown > 0){
			jumpCooldown--;
		}
		if (playerShotCd > 0){
			playerShotCd--;
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

	public boolean isCompletedLevel() {
		return completedLevel;
	}

	public void setCompletedLevel(boolean completedLevel) {
		this.completedLevel = completedLevel;
	}
}
