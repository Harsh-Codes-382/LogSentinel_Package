package io.logsentinel.starter.support;

import io.logsentinel.core.model.LogContext;
import io.logsentinel.core.model.LogEvent;
import io.logsentinel.core.model.LogLevel;

public class LogEventFactory {
    private final LogContext context;

    public LogEventFactory(LogContext context){
        this.context = context;
    }

    public LogEvent info(String message){
        return create(LogLevel.INFO, message);
    }

    public LogEvent error(String message, Throwable e){
        LogEvent event = create(LogLevel.ERROR, message);
        event.setErrorMessage(e.getMessage());
        event.setErrorType(e.getClass().getSimpleName());
        event.setStackTrace(buildStackTrace(e));
        return event;
    }

    private String buildStackTrace(Throwable e) {
        StringBuilder str = new StringBuilder();
        for(StackTraceElement el : e.getStackTrace()){
            str.append(el).append("\n");
        }
        return str.toString();
    }

    private LogEvent create(LogLevel logLevel, String message) {
        LogEvent event = new LogEvent();
        event.setContext(context);
        event.setMessage(message);
        event.setLevel(logLevel);
        return event;
    }
}
