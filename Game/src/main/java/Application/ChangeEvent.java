package Application;

public class ChangeEvent {
    public int level;
    public Application.GameState menu;
    public eventType type;

    public enum eventType{
        NONE,
        LEVEL_CHANGE,
        MENU_CHANGE,
        BACK
    }
}
