package com.company.gameConsole.exceptions;

public class InactiveException extends GameConsoleException{
    public InactiveException() {
        super("Game console turns off due to inactivity.");
    }
}
