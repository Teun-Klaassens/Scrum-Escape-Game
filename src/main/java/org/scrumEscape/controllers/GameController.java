package org.scrumEscape.controllers;

import java.util.Scanner;

public class GameController {
    private boolean play;

    public GameController() {
        this.play = true;
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Start!");

        while (play) {
            System.out.print("> ");
            String nextCommand = s.nextLine().toLowerCase();

            if (nextCommand.equals("stop")) {
                play = false;
                System.out.println("Stopped!");
            } else {
                System.out.println("Next line: " + nextCommand);
            }
        }
    }
}
