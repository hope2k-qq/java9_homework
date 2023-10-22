package com.company.game;

import com.company.game.enums.Genre;

public class Game {
    public enum Type {
        VIRTUAL, PHYSICAL
    }

    private final String name;
    private final Genre genre;
    private final Type type;

    private Game(String name, Genre genre, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Type getType() {
        return type;
    }

    public static GameDisk getDisk(String name, Genre genre, String description) {
        Game game = new Game(name, genre, Type.PHYSICAL);
        return new GameDisk(game, description);
    }

    public static VirtualGame getVirtualGame(String name, Genre genre) {
        Game game = new Game(name, genre, Type.VIRTUAL);
        return new VirtualGame(game);
    }

    public static class GameDisk{
        private final String description;
        private final Game data;

        private GameDisk(Game game, String description) {
            this.description = description;
            this.data = game;
        }

        public String getDescription() {
            return description;
        }

        public Game getDataGame() {
            return data;
        }

    }

    public static class VirtualGame{
        private int rating;
        private final Game data;

        private VirtualGame(Game game) {
            this.data = game;
        }

        public void setRating(int rating) {
            if (rating >= 0 && rating <= 5) {
                this.rating = rating;
            } else {
                throw new IllegalArgumentException("The rating must be from 0 to 5.");
            }
        }

        public int getRating() {
            return rating;
        }

        public Game getDataGame() {
            return data;
        }
    }
}
