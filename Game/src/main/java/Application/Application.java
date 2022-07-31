package Application;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame{

    public static int get5(){
        return 5;
    }

    public Application(){
        initUI();
    }

    private void initUI(){
        add(new Level());

        setSize(1000, 750);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
