package Application;

import Application.CustomGUI.Button;
import Application.CustomGUI.Text;
import Application.Menus.PauseMenu;
import Physics.MathVector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class Menu {

	protected ArrayList<Button> buttons;
	protected ArrayList<Text> texts;
	private Button buttonPressed;

	private MathVector scale = new MathVector(1.0, 1.0);

	public Menu(){
		buttons = new ArrayList<Button>();
		texts = new ArrayList<>();
		setupUI();
	}

	protected void setupUI(){

	}

	public void render(Graphics2D g2d, MathVector scale){
		this.scale = scale;

		for (var button : buttons){
			button.render(g2d, scale);
		}

		for (var text : texts){
			text.render(g2d, scale);
		}
	}


	// Event Handlers
	public ChangeEvent keyPressed(KeyEvent e){
		ChangeEvent event = new ChangeEvent(ChangeEvent.eventType.NONE);

		return event;
	}

	public ChangeEvent keyReleased(KeyEvent e){
		ChangeEvent event = new ChangeEvent(ChangeEvent.eventType.NONE);
		return event;
	}

	public ChangeEvent mousePressed(MouseEvent e){
		ChangeEvent event = new ChangeEvent(ChangeEvent.eventType.NONE);

		Point pos = new Point(e.getPoint()); // position of mouse

		// Checks if button has been pressed
		for (var button : buttons){
			if (button.isHoveredOver(Application.transfromMousePos(pos, scale))){
				buttonPressed = button;
			}
		}

		return event;
	}

	public ChangeEvent mouseReleased(MouseEvent e){
		ChangeEvent event = new ChangeEvent(ChangeEvent.eventType.NONE);

		Point pos = new Point(e.getPoint()); // position of mouse

		// Checks if the button released over on is the same as the button pressed
		// (Checks if the user actually  wants to press the button)
		Button buttonHovered = new Button();
		boolean hov = false;
		for (var button : buttons){
			if (button.isHoveredOver(Application.transfromMousePos(pos, scale))){
				buttonHovered = button;
				hov = true;
			}
		}

		if (hov){
			if (buttonPressed.equals(buttonHovered)){
				event = buttonPressed.onPress(); // Triggers the buttons functionality
			}
		}

		return event;
	}

	public ChangeEvent mouseMoved(MouseEvent e){
		ChangeEvent event = new ChangeEvent(ChangeEvent.eventType.NONE);

		Point pos = new Point(e.getPoint());

		for (var button : buttons){
			// Changes the buttons visibleColor depending if the button is hovered over or not
			button.setHover(button.isHoveredOver(Application.transfromMousePos(pos, scale)));
		}

		return event;
	}
}
