package Application;

import Application.CustomGUI.Text;
import Application.Levels.Level1;
import Game.Enemy;
import Game.Player;
import Game.Projectile;
import Game.Rigidbody;
import Physics.MathVector;


import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

// An abstract class that all levels extend
// Allows all levels to have the same base functionality and have unique level design
public abstract class Level{

	private ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Projectile> projs = new ArrayList<Projectile>();
	private Player player;

	protected ArrayList<Text> texts;

	private int levelID;

	MathVector offset = new MathVector(0.0, 0.0);

	public Level(){

		this.levelID = 0;

		// Sets up level design
		initLevel();
	}

	public Level(int levelID){
		this.levelID = levelID;

		initLevel();
	}

	public Level(Level another){
		this.rbs = another.rbs;
		this.enemies = another.enemies;
		this.projs = another.projs;
		this.player = another.player;
		this.levelID = another.levelID;
		this.offset = another.offset;
	}


	// This method is a template method that every level overides
	// Sets up the level design, eg platforms, enemies etc
	protected void initLevel(){

	}

	void calculateOffset(Dimension screensize, double scaleBy){

		double buffer = 2.5;

		double scw = ((double)screensize.width/(scaleBy))*(2.0/3.0);

		if (player.getMidPos().getX() + offset.getX() - scw > buffer)
			offset.setX(player.getMidPos().getX()-buffer-scw);

		else if (player.getMidPos().getX() + offset.getX() - scw < -buffer)
			offset.setX(player.getMidPos().getX()+buffer-scw);

		offset.setY(player.getMidPos().getY()-(double)screensize.height/(scaleBy));

	}

	// Renders the level, all of the rigidbodies and the player

	public void renderLevel(Graphics2D g2d, Dimension screensize, MathVector scale, double scaleBy){

		//Dimension screensize = getSize();
		//scale = new MathVector(screensize.height/scaleBy, screensize.height/scaleBy);
		calculateOffset(screensize, scaleBy);

		for (Rigidbody rb : rbs) {
			rb.render(g2d, offset, scale);
		}

		for (Enemy enemy : enemies) {
			enemy.render(g2d, offset, scale, Color.RED);
		}

		for (int i=0; i<projs.size(); i++) {
			projs.get(i).render(g2d, offset, scale, Color.RED);
		}
		player.render(g2d, offset, scale, Color.BLUE);

		for (var text : texts){
			text.render(g2d, scale);
		}

	}


	public ChangeEvent update(int delay){

		ChangeEvent ev = new ChangeEvent();
		player.update(delay, rbs);

		for (Enemy enemy : enemies) {
			enemy.update(delay, rbs);
			Projectile proj = enemy.shoot(player.getPos());
			if (proj != null){
				addProj(proj);
			}
		}

		ArrayList<Rigidbody> projRbs = new ArrayList<>();
		projRbs.add(player);
		projRbs.addAll(rbs);
		for (Projectile proj : projs){
			proj.update(delay, projRbs);
		}

		for (int i=0; i<projs.size(); i++){
			if(projs.get(i).collided){
				projs.remove(i);
			}
		}


		for (Rigidbody rb : rbs){
			rb.update(delay, rbs);
		}


		if (player.isCompletedLevel()){
			ev.type = ChangeEvent.eventType.LEVEL_COMPLETE;
			ev.level = levelID;
			ev.health = player.getHealth();
			ev.maxHealth = player.getMaxHealth();
		} else if (player.isDead()){
			ev.type = ChangeEvent.eventType.LEVEL_CHANGE;
			ev.level = levelID;
		}

		return ev;
	}



	// Handles inputs while playing
	public ChangeEvent keyPressed(KeyEvent e) {
		ChangeEvent event = new ChangeEvent();
		event.type = ChangeEvent.eventType.NONE;
		if (player.handlePress(e)) {

		}
		else{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE){
				event.type = ChangeEvent.eventType.MENU_CHANGE;
				event.menu = Application.GameState.PAUSE;
			}
		}
		return event;
	}

	public ChangeEvent keyReleased(KeyEvent e){
		ChangeEvent event = new ChangeEvent();
		event.type = ChangeEvent.eventType.NONE;
		if (player.handleRelease(e)) {

		}
		else{
			int key = e.getKeyCode();

		}
		return event;
	}

	public ChangeEvent mouseClicked(MouseEvent me){
		ChangeEvent event = new ChangeEvent();
		event.type = ChangeEvent.eventType.NONE;
		return event;
	}

	public ChangeEvent mouseReleased(MouseEvent me){
		ChangeEvent event = new ChangeEvent();
		event.type = ChangeEvent.eventType.NONE;
		return event;
	}


	// Getters and Setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addToRBS(Rigidbody rb){
		rbs.add(rb);
	}

	public void addEnemy(Enemy enemy){
		enemies.add(enemy);
	}

	public void addProj(Projectile proj){
		projs.add(proj);
	}

	public int getLevelID() {
		return levelID;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public abstract Level clone(int levelID);

	// State of the game:
	// GAME: playing the game
	// PAUSE: the pause menu
	// OPTIONS: the options menu
	protected enum State{
		PLAY,
		PAUSE,
		OPTIONS
	}
}
