package xoapp.view;

import xoapp.viewmodel.GameViewModel;

import javax.swing.*;
import java.awt.*;

public class SinglePlayerView extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private GameViewModel viewModel = new GameViewModel();

    public SinglePlayerView() {
        setTitle("XO - Single Player");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                final int row = i, col = j;

                btn.addActionListener(e -> {
                    if (viewModel.makeMove(row, col)) {
                        btn.setText(viewModel.getCurrentSymbol());
                        if (viewModel.checkWinner()) {
                            JOptionPane.showMessageDialog(this, viewModel.getWinner() + " wins!");
                            resetBoard();
                        } else {
                            viewModel.switchPlayer();
                        }
                    }
                });

                buttons[i][j] = btn;
                boardPanel.add(btn);
            }

        JButton restartBtn = new JButton("Restart");
        restartBtn.addActionListener(e -> resetBoard());

        add(boardPanel, BorderLayout.CENTER);
        add(restartBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void resetBoard() {
        viewModel.resetGame();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
    }
}
