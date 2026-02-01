package io.logsentinel.starter.support;

import io.logsentinel.core.model.LogContext;
import io.logsentinel.core.model.LogEvent;
import io.logsentinel.core.model.LogLevel;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class LogEventFactory {

    public static final String DEFAULT_SCHEMA_VERSION = "v1";

    private final LogContext context;

    public LogEventFactory(LogContext context) {
        this.context = context;
    }

    /* ---------> INFO <--------- */

    public LogEvent info(String message) {
        return info(message, DEFAULT_SCHEMA_VERSION);
    }

    public LogEvent info(String message, String schemaVersion) {
        return create(LogLevel.INFO, message, "INFO", schemaVersion);
    }

    /* ---------> ERROR <--------- */

    public LogEvent error(String message, Throwable e) {
        return error(message, e, DEFAULT_SCHEMA_VERSION);
    }

    public LogEvent error(String message, Throwable e, String schemaVersion) {
        LogEvent event = create(LogLevel.ERROR, message, "ERROR", schemaVersion);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("error.type", e.getClass().getName());
        attributes.put("error.message", e.getMessage());
        attributes.put("error.stacktrace", buildStackTrace(e));

        event.setAttributes(attributes);
        return event;
    }

    /* ---------> SUCCESS <--------- */

    public LogEvent success(String message) {
        return success(message, DEFAULT_SCHEMA_VERSION);
    }

    public LogEvent success(String message, String schemaVersion) {
        return create(LogLevel.INFO, message, "SUCCESS", schemaVersion);
    }

    /* ---------> CORE BUILDER <--------- */

    private LogEvent create(LogLevel logLevel,
                            String message,
                            String eventType,
                            String schemaVersion) {

        LogEvent event = new LogEvent();
        event.setLevel(logLevel);
        event.setMessage(message);
        event.setTimestamp(Instant.now());
        event.setContext(context);
        event.setEventType(eventType);
        event.setSchemaVersion(schemaVersion);

        return event;
    }

    private String buildStackTrace(Throwable e) {
        StringBuilder str = new StringBuilder();
        for (StackTraceElement el : e.getStackTrace()) {
            str.append(el).append('\n');
        }
        return str.toString();
    }
}