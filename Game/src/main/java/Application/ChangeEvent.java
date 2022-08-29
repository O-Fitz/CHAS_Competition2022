package Application;

public class ChangeEvent {
    public int level;
    public Application.GameState menu;
    public eventType type;

    public ChangeEvent(){

    }

    public ChangeEvent(eventType type){
        this.type = type;
    }

    public enum eventType{
        NONE,
        LEVEL_CHANGE,
        MENU_CHANGE,
        BACK
    }
}
