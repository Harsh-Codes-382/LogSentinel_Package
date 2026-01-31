package io.logsentinel.starter.autoconfig;

import io.logsentinel.core.LogClient;
import io.logsentinel.core.model.LogContext;
import io.logsentinel.core.sender.LogSender;
import io.logsentinel.core.sender.async.AsyncLogSender;
import io.logsentinel.core.sender.http.HttpLogSender;
import io.logsentinel.starter.config.LogSentinelProperties;
import io.logsentinel.starter.support.LogEventFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AutoConfiguration
@EnableConfigurationProperties(LogSentinelProperties.class)
public class LogSentinelAutoConfiguration {

    @Bean
    public ExecutorService logSenderExecutor(){
        return Executors.newFixedThreadPool(2);
    }

    @Bean
    public LogContext logContext(LogSentinelProperties props){
        LogContext context = new LogContext();
        context.setEnvironment(props.getEnvironment());
        context.setServiceName(props.getServiceName());
        return context;
    }

    @Bean
    @ConditionalOnProperty(
            prefix="log.sentinel",
            name = "enabled",
            havingValue = "true"
    )
    public LogSender logSender(LogSentinelProperties props, ExecutorService logSenderExecutor){
        LogSender httpSender = new HttpLogSender(props.getEndpoint(), props.getApiKey());

        return new AsyncLogSender(httpSender, logSenderExecutor);
    }

    @Bean
    public LogClient logClient(LogSender sender){
        return new LogClient(sender);
    }

    @Bean
    public LogEventFactory logEventFactory(LogContext context){
        return new LogEventFactory(context);
    }
}
