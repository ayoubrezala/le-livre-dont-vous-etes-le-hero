package main;

public class InvalidBookException extends Exception{
    public InvalidBookException() {
        super("Invalid book!");
    }

    public InvalidBookException(String message) {
        super(message);
    }

}
