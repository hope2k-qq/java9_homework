package com.company.playRoom;

import com.company.game.Game;
import com.company.game.enums.Genre;
import com.company.gameConsole.GameConsole;
import com.company.gameConsole.enums.Brand;

import java.util.*;

public class PlayRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Game.GameDisk[] physicalGames = new Game.GameDisk[4];
        physicalGames[0] = Game.getDisk("Physical game 1", Genre.ACTION, "bla bla");
        physicalGames[1] = Game.getDisk("Physical game 2", Genre.RACE, "bla bla bla");
        physicalGames[2] = Game.getDisk("Physical game 3", Genre.RACE, "bla bla");
        physicalGames[3] = Game.getDisk("Physical game 4", Genre.SPORT, "bla bla bla");

        Game.VirtualGame[] virtualGames = new Game.VirtualGame[4];
        virtualGames[0] = Game.getVirtualGame("Virtual game 1", Genre.RACE);
        virtualGames[0].setRating(5);
        virtualGames[1] = Game.getVirtualGame("Virtual game 2", Genre.ACTION);
        virtualGames[1].setRating(3);
        virtualGames[2] = Game.getVirtualGame("Virtual game 3", Genre.SPORT);
        virtualGames[2].setRating(1);
        virtualGames[3] = Game.getVirtualGame("Virtual game 4", Genre.SPORT);
        virtualGames[3].setRating(5);

        GameConsole gameConsole = new GameConsole(Brand.MICROSOFT, "w88II0nGmh");

        System.out.println("\nNot sorted by genre(Physical game): ");
        for (Game.GameDisk game : physicalGames) {
            System.out.println(game.getDataGame().getName() + ", Genre: " + game.getDataGame().getGenre());
        }

        Arrays.sort(physicalGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk game1, Game.GameDisk game2) {
                return game1.getDataGame().getGenre().compareTo(game2.getDataGame().getGenre());
            }
        });

        System.out.println("\nSorted by genre(Physical game): ");
        for (Game.GameDisk game : physicalGames) {
            System.out.println(game.getDataGame().getName() + ", Genre: " + game.getDataGame().getGenre());
        }


        System.out.println("\nNot sorted by rating(Virtual game): ");
        for (Game.VirtualGame game : virtualGames) {
            System.out.println(game.getDataGame().getName() + ", Genre: " + game.getDataGame().getGenre() +
                    ", Rating: " + game.getRating());
        }

        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame game1, Game.VirtualGame game2) {
                return Integer.compare(game1.getRating(), game2.getRating());
            }
        });

        System.out.println("\nSorted by rating(Virtual game): ");
        for (Game.VirtualGame game : virtualGames) {
            System.out.println(game.getDataGame().getName() + ", Genre: " + game.getDataGame().getGenre() +
                    ", Rating: " + game.getRating());
        }
        System.out.println();
        System.out.println();
        int value;
        while (true) {
            System.out.println("\nLoad a random physics game - 1");
            System.out.println("Load a random virtual game - 2");
            System.out.println("Play game - 3");
            System.out.println("Connect gamepad - 4");
            System.out.println("Exit - 5");
            try{
                System.out.print("\nEnter value: ");
                value = scanner.nextInt();
                scanner.nextLine();
                switch (value) {
                    case 1:
                        gameConsole.loadGame(physicalGames[random.nextInt(physicalGames.length)].getDataGame());
                        break;
                    case 2:
                        gameConsole.loadGame(virtualGames[random.nextInt(virtualGames.length)].getDataGame());
                        break;
                    case 3:
                        gameConsole.playGame();
                        break;
                    case 4:
                        System.out.print("Enter the gamepad number (1 or 2): ");
                        int gamepadNumber = scanner.nextInt();
                        scanner.nextLine();
                        gameConsole.connectGamepad(gamepadNumber);
                        break;
                    case 5:
                        System.out.println("Exit.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Enter the correct value.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("It's not a number.");
                scanner.next();
            }
        }
    }
}
