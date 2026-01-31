package io.logsentinel.core.sender;

public class SendResult {
    private final boolean success;
    private final int statusCode;
    private final String message;

    public SendResult(boolean success, int statusCode, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
    }

    public static SendResult success(int StatusCode){
        return new SendResult(true, StatusCode, null);
    }

    public static SendResult failure(int StatusCode, String message){
        return new SendResult(true, StatusCode, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
