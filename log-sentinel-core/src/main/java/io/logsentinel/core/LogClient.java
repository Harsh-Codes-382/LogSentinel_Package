package io.logsentinel.core;

import io.logsentinel.core.model.LogEvent;
import io.logsentinel.core.sender.LogSender;
import io.logsentinel.core.sender.SendResult;
import io.logsentinel.core.sender.SenderException;

public class LogClient {
    private final LogSender sender;

    public LogClient(LogSender sender) {
        this.sender = sender;
    }

    public SendResult send(LogEvent event){
        try {
            return sender.send(event);
        }
        catch (SenderException e){
            throw new RuntimeException("Log Sending failed", e);
        }
    }
}
