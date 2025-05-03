package xoapp.viewmodel;

public class GameViewModel {
    private String[][] board = new String[3][3];
    private String currentPlayer = "X";
    private String winner = null;

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (equal(board[i][0], board[i][1], board[i][2]) ||
                equal(board[0][i], board[1][i], board[2][i]))
                return true;
        }
        return equal(board[0][0], board[1][1], board[2][2]) ||
               equal(board[0][2], board[1][1], board[2][0]);
    }

    private boolean equal(String a, String b, String c) {
        if (a == null) return false;
        if (a.equals(b) && b.equals(c)) {
            winner = a;
            return true;
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public String getCurrentSymbol() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public void resetGame() {
        board = new String[3][3];
        currentPlayer = "X";
        winner = null;
    }
}
