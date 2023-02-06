package me.trubnikova.cookbook.validate;

public class InvalidInputException extends Exception{

    public InvalidInputException(String message) {
        super(message);
    }
}
