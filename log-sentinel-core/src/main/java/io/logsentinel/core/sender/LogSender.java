package io.logsentinel.core.sender;

import io.logsentinel.core.model.LogEvent;

public interface LogSender {
    /**
     * Sends a log event to a remote destination.
     *
     * @param event log event to send
     * @return result of sending operation
     * @throws SenderException when sending fails hard
     */
    SendResult send(LogEvent event) throws SenderException;
}
