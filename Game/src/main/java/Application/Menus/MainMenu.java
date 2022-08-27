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

        playButton.setPreferredSize(new Dimension(100, 100));

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener((event)-> System.exit(0));
        quitButton.setPreferredSize(new Dimension(100, 100));

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quitButton)
                .addComponent(playButton));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(quitButton)
                        .addComponent(playButton))
        );

        this.setLayout(gl);


    }



}
