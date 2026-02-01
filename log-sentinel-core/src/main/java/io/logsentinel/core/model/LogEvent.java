package io.logsentinel.core.model;

import java.time.Instant;
import java.util.Map;

public class LogEvent {
    // Core envelope
    private LogLevel level;
    private String message;
    private Instant timestamp;
    private LogContext context;

    // Client-defined attributes
    private Map<String, Object> attributes;

    // Classification
    private String eventType;      // PAYMENT_FAILED, USER_LOGIN
    private String schemaVersion;  // v1, v2

    public LogEvent(){
        this.timestamp = Instant.now();
    }

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

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public LogContext getContext() {
        return context;
    }

    public void setContext(LogContext context) {
        this.context = context;
    }
}
