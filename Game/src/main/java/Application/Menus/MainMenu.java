package Application.Menus;

import javax.swing.*;
import java.awt.Dimension;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class MainMenu extends JPanel {

    public MainMenu(){
        initUI();
    }


    private void initUI(){
        GroupLayout gl = new GroupLayout(this);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        JButton playButton = new JButton("Play");

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener((event)-> System.exit(0));
        quitButton.setPreferredSize(new Dimension(100, 100));

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quitButton));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(quitButton))
        );

        this.setLayout(gl);


    }



}
