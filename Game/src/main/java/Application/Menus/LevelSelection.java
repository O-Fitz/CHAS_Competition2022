package Application.Menus;

import Application.ChangeEvent;
import Application.CustomGUI.Button;
import Application.CustomGUI.FunctionCall;
import Application.CustomGUI.Star;
import Application.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LevelSelection extends Menu {

	private HashMap<Integer, Integer> scores;

	@Override
	protected void setupUI() {

		scores = new HashMap<>();
		readScore();

		// Level 1 selector
		Point pos = new Point(10, 10);
		Dimension size = new Dimension(10, 5);
		FunctionCall fun = () -> {
			ChangeEvent event = new ChangeEvent();
			event.type = ChangeEvent.eventType.LEVEL_CHANGE;
			event.level = 1;
			return event;
		};
		Application.CustomGUI.Button b = new Button(pos, size, "LEVEL 1", fun);
		buttons.add(b);

		// Level 2 selector
		Point pos2 = new Point(25, 10);
		FunctionCall fun2 = () -> {
			ChangeEvent event = new ChangeEvent();
			event.type = ChangeEvent.eventType.LEVEL_CHANGE;
			event.level = 2;
			return event;
		};
		Application.CustomGUI.Button b2 = new Button(pos2, size, "LEVEL 2", fun2);
		buttons.add(b2);

		addStars();

	}

	public void updateScore(int levelID, int score){
		if (scores.containsKey(levelID)){
			scores.put(levelID, Math.max(score, scores.get(levelID)));
		}else{
			scores.put(levelID, score);
		}
		writeScore();
		addStars();
	}

	private void writeScore(){
		//JSONObject jsonObject = new JSONObject();

		try {
			File file = new File("Saves/save1.txt");

			if (!file.exists()){
				file.createNewFile();
			}

			String str = "";
			for (var item : scores.entrySet()){
				str = str.concat(String.format("%d %d\n", item.getKey(), item.getValue()));
			}

			FileWriter myWriter = new FileWriter("Saves/save1.txt");
			myWriter.write(str);
			myWriter.close();

		} catch (IOException e) {
			System.out.println("An error occurred while saving.");
			e.printStackTrace();
		}
	}

	private void readScore(){
		try {
			File file = new File("Saves/save1.txt");

			if (!file.exists()){
				file.createNewFile();
			}

			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String[] data = myReader.nextLine().split(" ");
				int key = Integer.parseInt(data[0]);
				int value = Integer.parseInt(data[1]);
				scores.put(key, value);
			}
			myReader.close();


		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		for (var item : scores.entrySet()){
			System.out.printf("%d %d\n", item.getKey(), item.getValue());
		}
	}

	private void addStars(){
		stars = new ArrayList<>();
		for (var level : scores.entrySet()){
			for (int i=0; i<level.getValue(); i++){
				Point pos = new Point((int)Math.round((i*4)+16.5*level.getKey()-6), 18+5*(level.getKey()/4));
				Star star = new Star(pos, new Dimension(2, 2), new Color(246, 229, 52));
				stars.add(star);
			}
		}
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
