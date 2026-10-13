package com.shivansh.devops_copilot.tools;

import com.shivansh.devops_copilot.client.GitHubClient;
import com.shivansh.devops_copilot.client.GitHubRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GithubTools {

    private final GitHubClient gitHubClient;

    public GithubTools(GitHubClient gitHubClient)
    {
        this.gitHubClient=gitHubClient;
    }
    @Tool(description = "Use this tool when the user asks to list, inspect, or discover the public repositories of a GitHub username. Requires a GitHub username as input")
    public List<GitHubRepository> getRepositories(String username)
    {
        return gitHubClient.getAllByUsername(username);
    }
}
