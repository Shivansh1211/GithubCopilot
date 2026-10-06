package com.shivansh.devops_copilot.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubRepository(
        Long id,
        String name,
        String language,
        String description,
         @JsonProperty("private")
        boolean privateRepo,
        @JsonProperty("html_url")
        String htmlUrl,
        @JsonProperty("default_branch")
        String defaultBranch
) {
}
