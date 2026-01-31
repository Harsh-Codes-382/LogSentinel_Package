package io.logsentinel.core.sender.async;

import io.logsentinel.core.model.LogEvent;
import io.logsentinel.core.sender.LogSender;
import io.logsentinel.core.sender.SendResult;
import io.logsentinel.core.sender.SenderException;

import java.util.concurrent.ExecutorService;

public class AsyncLogSender implements LogSender {

    private final LogSender delegate;
    private final ExecutorService executor;

    public AsyncLogSender(LogSender delegate, ExecutorService executor) {
        this.delegate = delegate;
        this.executor = executor;
    }

    @Override
    public SendResult send(LogEvent event) throws SenderException {
        executor.submit(() -> {
            try{
                delegate.send(event);
            }catch (Exception e){
                // Do not throw error
            }
        });
        return SendResult.success(200);
    }
}
