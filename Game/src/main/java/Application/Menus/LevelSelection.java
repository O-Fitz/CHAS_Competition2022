package Application.Menus;

import Application.ChangeEvent;
import Application.CustomGUI.Button;
import Application.CustomGUI.FunctionCall;
import Application.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelSelection extends Menu {

	@Override
	protected void setupUI(){

		// Level 1 selector
		Point pos = new Point(10, 10);
		Dimension size = new Dimension(10, 5);
		FunctionCall fun = ()->{
			ChangeEvent event = new ChangeEvent();
			event.type = ChangeEvent.eventType.LEVEL_CHANGE;
			event.level = 1;
			return event;
		};
		Application.CustomGUI.Button b = new Button(pos, size, "LEVEL 1", fun);
		buttons.add(b);

	}

	@Override
	public ChangeEvent keyPressed(KeyEvent e){
		ChangeEvent event = new ChangeEvent();
		event.type = ChangeEvent.eventType.NONE;

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE){
			event.type=ChangeEvent.eventType.BACK;
		}

		return event;
	}

}
