package xoapp.network;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is waiting for players...");

            Socket playerOneSocket = serverSocket.accept();
            System.out.println("Player One connected.");

            Socket playerTwoSocket = serverSocket.accept();
            System.out.println("Player Two connected.");

            new Thread(new ServerHandler(playerOneSocket, playerTwoSocket)).start();
            new Thread(new ServerHandler(playerTwoSocket, playerOneSocket)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
