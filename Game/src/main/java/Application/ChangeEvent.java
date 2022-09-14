package Application;

public class ChangeEvent {
	public int level;
	public Application.GameState menu;
	public eventType type;
	public int health;
	public int maxHealth;

	public ChangeEvent(){
		type = eventType.NONE;
	}

	public ChangeEvent(eventType type){
		this.type = type;
	}

	public enum eventType{
		NONE,
		LEVEL_CHANGE,
		MENU_CHANGE,
		BACK,
		LEVEL_COMPLETE
	}
}
