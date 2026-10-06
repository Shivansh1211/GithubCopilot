package com.shivansh.devops_copilot.dto;
import com.fasterxml.jackson.annotation.JsonProperty;


public record RepositoryResponse (
        Long id,
        String name,
        String language,
        String description,
        boolean privateRepo,
        String url,
        String defaultBranch


)
{}
