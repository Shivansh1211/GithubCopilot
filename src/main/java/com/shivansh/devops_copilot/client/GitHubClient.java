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
                .build();
            }

    public List<GitHubRepository> getPublicReposioties(String username)
    {
        return restClient.get()
                .uri("/users/{username}/repos",username)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GitHubRepository>> () {});
    }
}


