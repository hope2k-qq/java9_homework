package com.company.gameConsole;

import com.company.game.Game;
import com.company.gameConsole.enums.Brand;
import com.company.gameConsole.enums.Color;
import com.company.gameConsole.exceptions.InactiveException;
import com.company.gameConsole.interfaces.Powered;

public class GameConsole implements Powered {
    private final Brand brand;
    private final String model;
    private final String serial;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.model = "";
        this.serial = serial;
        this.firstGamepad = new Gamepad(brand, 1);
        this.secondGamepad = new Gamepad(brand, 2);
        this.isOn = false;
    }

    @Override
    public void powerOn() {
        isOn = true;
        waitingCounter = 0;
    }

    @Override
    public void powerOff() {
        isOn = false;
        waitingCounter = 0;
    }

    public void connectGamepad(int gamepadNumber) {
        Gamepad gamepadToConnect = null;

        if (gamepadNumber == 1) {
            gamepadToConnect = firstGamepad;
        } else if (gamepadNumber == 2) {
            gamepadToConnect = secondGamepad;
        } else {
            System.out.println("Invalid gamepad number.");
            return;
        }

        if (gamepadToConnect != null) {
            gamepadToConnect.powerOn();
            System.out.println("Gamepad " + gamepadNumber + " powerOn.");
        }
    }

    public void loadGame(Game game) {
        activeGame = game;
        System.out.println("Game \"" + game.getName() + "\" loading.");
    }

    public void playGame() {
        if (activeGame != null) {
            boolean isGamepadActive = checkStatus();
            if(isGamepadActive){
                System.out.println("Playing " + activeGame.getName());
                if (firstGamepad.isOn()) {
                    firstGamepad.reduceChargeLevel(10);
                    if (firstGamepad.getChargeLevel() == 0) {
                        firstGamepad.powerOff();
                    }
                }
                if (secondGamepad.isOn()) {
                    secondGamepad.reduceChargeLevel(10);
                    if (secondGamepad.getChargeLevel() == 0) {
                        secondGamepad.powerOff();
                    }
                }
            }
        } else {
            System.out.println("Not active game.");
        }
    }

    private boolean checkStatus() {
        boolean isGamepadActive = firstGamepad.isOn() || secondGamepad.isOn();

        if (!isGamepadActive) {
            System.out.println("Pls, connect the gamepad");
            waitingCounter++;
        } else {
            waitingCounter = 0;
        }

        try{
            if (waitingCounter > 5) {
                powerOff();
                throw new InactiveException();
            }
        } catch (InactiveException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isGamepadActive;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public boolean isOn() {
        return isOn;
    }

    public Game getActiveGame() {
        return activeGame;
    }

    public int getWaitingCounter() {
        return waitingCounter;
    }

    public class Gamepad implements Powered{
        private final Brand brand;
        private final String consoleSerial;
        private final int connectedNumber;
        private Color color;
        private double chargeLevel;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.consoleSerial = GameConsole.this.serial;
            this.connectedNumber = connectedNumber;
            this.color = Color.WHITE;
            this.chargeLevel = 100.0;
            this.isOn = false;
        }

        @Override
        public void powerOn() {
            isOn = true;
            GameConsole.this.powerOn();
        }

        @Override
        public void powerOff() {
            isOn = false;
            GameConsole.this.checkStatus();
        }

        public void reduceChargeLevel(double amount) {
            if (chargeLevel > 0) {
                chargeLevel -= amount;
            }
        }

        public Brand getBrand() {
            return brand;
        }

        public String getConsoleSerial() {
            return consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }


        public void setColor(Color color) {
            this.color = color;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }
    }
}
