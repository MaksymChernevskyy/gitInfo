package com.devapo.services;

import com.devapo.exceptions.BadRequestException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.springframework.http.HttpStatus.OK;

@Component
public class HttpService {

    private final HttpClient client;

    public HttpService(TaskExecutor taskExecutor) {
        client = HttpClient.newBuilder()
                .executor(taskExecutor)
                .build();
    }

    String sendRequest(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::validateStatus)
                .join();
    }

    private <T> T validateStatus(HttpResponse<T> response) {
        if (HttpStatus.valueOf(response.statusCode()) != OK) {
            throw new BadRequestException(String.format(
                    "Http request returned status code: %d. Body: %s",
                    response.statusCode(),
                    response.body()
            ));
        }
        return response.body();
    }
}
