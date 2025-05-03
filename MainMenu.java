package xoapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("XO Game - Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton singlePlayerBtn = new JButton("Single Player");
        JButton multiPlayerBtn = new JButton("Multiplayer");

        singlePlayerBtn.addActionListener((ActionEvent e) -> new SinglePlayerView());

        multiPlayerBtn.addActionListener((ActionEvent e) -> new MultiplayerLauncher());

        add(singlePlayerBtn);
        add(multiPlayerBtn);

        setVisible(true);
    }
}
