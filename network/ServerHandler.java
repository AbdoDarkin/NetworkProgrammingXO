package xoapp.network;

import java.io.*;
import java.net.*;

public class ServerHandler implements Runnable {
    private Socket currentPlayerSocket;
    private Socket opponentSocket;

    public ServerHandler(Socket currentPlayerSocket, Socket opponentSocket) {
        this.currentPlayerSocket = currentPlayerSocket;
        this.opponentSocket = opponentSocket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(currentPlayerSocket.getInputStream()));
            PrintWriter opponentOut = new PrintWriter(opponentSocket.getOutputStream(), true)
        ) {
            String input;
            while ((input = in.readLine()) != null) {
                opponentOut.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
