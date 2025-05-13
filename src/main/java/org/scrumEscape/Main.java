package org.scrumEscape;

import org.scrumEscape.controllers.GameController;
import org.scrumEscape.controllers.MenuController;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuController.printWelcome();
        GameController gameController = new GameController(scanner);
        gameController.start();

        scanner.close();
        MenuController.applicationClosing();
     }
}