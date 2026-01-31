package io.logsentinel.core.sender.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.logsentinel.core.JsonMapper;
import io.logsentinel.core.model.LogEvent;
import io.logsentinel.core.sender.LogSender;
import io.logsentinel.core.sender.SendResult;
import io.logsentinel.core.sender.SenderException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpLogSender implements LogSender {

    private final HttpClient httpClient;
    private final URI endpoint;
    private final String apiKey;

    public HttpLogSender(String endpoint, String apiKey){
        this.endpoint = URI.create(endpoint);
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Override
    public SendResult send(LogEvent event) throws SenderException {
        try{
            String payload = serialize(event);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(this.endpoint)
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .header("X-API-KEY", this.apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            if(status >= 200 && status < 300){
                return SendResult.success(status);
            }

            return SendResult.failure(status, response.body());

        }
        catch (Exception e){
            throw new SenderException("Failed to send log event", e);
        }
    }

    /**
     * TEMP serializer (we’ll improve later)
     */
    private String serialize(LogEvent event) throws JsonProcessingException {
        try{
            return JsonMapper.get().writeValueAsString(event);
        }
        catch (JsonProcessingException e){
            throw new RuntimeException("Failed to serialize log", e);
        }
    }
}
