package com.devapo.services;

import com.devapo.entity.GitInfoOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitOutputService {
    private HttpService httpService;
    private JsonConverter jsonConverter;

    @Autowired
    public GitOutputService(HttpService httpService, JsonConverter jsonService) {
        this.httpService = httpService;
        this.jsonConverter = jsonService;
    }

    public GitInfoOutput getRepositoryInfo(String user, String repository) throws IOException {
        String uri = String.format("https://api.github.com/repos/%s/%s", user, repository);
        String response = httpService.sendRequest(uri);
        return jsonConverter.convert(response);
    }
}
