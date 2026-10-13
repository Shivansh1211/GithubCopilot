package com.shivansh.devops_copilot.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GitHubClient {

    private final RestClient restClient;

    public GitHubClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.github.com")
                .defaultHeader("User-Agent", "DevOps-Copilot-App")
                .build();
    }

    public List<GitHubRepository> getAllByUsername(String username) {
        return restClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GitHubRepository>>() {});
    }
}


