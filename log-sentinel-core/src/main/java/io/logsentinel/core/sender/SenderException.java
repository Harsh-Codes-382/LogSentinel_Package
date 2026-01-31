package io.logsentinel.core.sender;

public class SenderException extends Exception{
    public SenderException(String message) {
        super(message);
    }

    public SenderException(String message, Throwable cause) {
        super(message, cause);
    }
}
