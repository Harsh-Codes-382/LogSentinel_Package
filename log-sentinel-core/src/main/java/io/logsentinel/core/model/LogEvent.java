package io.logsentinel.core.model;

import java.time.Instant;
import java.util.Map;

public class LogEvent {
    private LogLevel level;
    private String message;
    private Instant timestamp;

    // Optional structured data
    private Map<String, Object> data;

    // Error info
    private String errorType;
    private String errorMessage;
    private String stackTrace;

    private LogContext context;

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public LogContext getContext() {
        return context;
    }

    public void setContext(LogContext context) {
        this.context = context;
    }
}
