package Game;

import Physics.MathVector;

import java.awt.*;

public class Entity extends DynamicRigidbody{

	protected int maxHealth;
	protected int health;

	private final int minHieght = 90;


	// Constructors
	public Entity(){
		super();
		this.maxHealth = 20;
		this.health = maxHealth;
	}

	public Entity(int maxHealth) {
		super();
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	public Entity(MathVector size, int maxHealth) {
		super(size);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	public Entity(MathVector size, MathVector position, int maxHealth) {
		super(size, position);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	public Entity(MathVector size, MathVector position, MathVector velocity, int maxHealth) {
		super(size, position, velocity);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	@Override
	protected void onProjectileCollision(Projectile proj){
		health -= proj.getDamage();
	}

	public boolean isDead(){
		if (health<=0) return true;
		if (getPos().getY()>minHieght) return true;
		return false;
	}

	// Setters and Getters

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
