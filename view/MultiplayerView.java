package xoapp.view;

import xoapp.viewmodel.GameViewModel;
import xoapp.network.Client;

import javax.swing.*;
import java.awt.*;

public class MultiplayerView extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private GameViewModel viewModel;
    private boolean isMyTurn;
    private String mySymbol;
    private Client client;

    public MultiplayerView(String title, boolean startTurn, String symbol, String serverHost) {
        setTitle(title);
        setSize(400, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.viewModel = new GameViewModel();
        this.isMyTurn = startTurn;
        this.mySymbol = symbol;

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 40);

        client = new Client(serverHost, move -> {
            String[] parts = move.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            String opponentSymbol = mySymbol.equals("X") ? "O" : "X";
buttons[row][col].setText(opponentSymbol);
viewModel.makeMove(row, col);

if (viewModel.checkWinner()) {
    JOptionPane.showMessageDialog(this, opponentSymbol + " wins!");
    resetBoard();
    return;
}

viewModel.switchPlayer();
isMyTurn = true;

        });

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                final int row = i, col = j;

                btn.addActionListener(e -> {
                    if (btn.getText().equals("") && isMyTurn) {
                        btn.setText(mySymbol);
                        viewModel.makeMove(row, col);
                        client.sendMove(row + "," + col);
                        isMyTurn = false;

                        if (viewModel.checkWinner()) {
    JOptionPane.showMessageDialog(this, mySymbol + " wins!");
    resetBoard();
    return;
}

                        viewModel.switchPlayer();
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
        isMyTurn = mySymbol.equals("X");
    }
}
