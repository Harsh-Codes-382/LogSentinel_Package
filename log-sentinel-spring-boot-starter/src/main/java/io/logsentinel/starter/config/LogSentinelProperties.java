package io.logsentinel.starter.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "log.sentinel")
@Validated
public class LogSentinelProperties {
    // Master switch
    private boolean enabled = true;
    // Async Configurable
    private boolean async = true;

    // Log ingestion endpoint
    @NotBlank
    private String endpoint;

    // Auth / API key
    private String apiKey;

    // Service identity
    @NotBlank(message = "Please specify the Service Name")
    private String serviceName;
    @NotBlank(message = "Please specify the Environment")
    private String environment;

    @Valid
    private Executor executor = new Executor();

    public static class Executor {

        @Min(1)
        @Max(32)
        private int threads = 2;

        public int getThreads() {
            return threads;
        }

        public void setThreads(int threads) {
            this.threads = threads;
        }
    }


    // Timeouts
    private int connectTimeoutMs = 2000;
    private int readTimeoutMs = 5000;

    public Executor getExecutor() {
        return this.executor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAsync() {
        return async;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public void setConnectTimeoutMs(int connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }

    public int getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public void setReadTimeoutMs(int readTimeoutMs) {
        this.readTimeoutMs = readTimeoutMs;
    }
}
