package xoapp.view;

import xoapp.network.Server;

public class MultiplayerLauncher {

    public MultiplayerLauncher() {
        new Thread(() -> Server.main(null)).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                new MultiplayerView("Player One (O)", true, "O", "localhost");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1500);
                new MultiplayerView("Player Two (X)", false, "X", "localhost");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
