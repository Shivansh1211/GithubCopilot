package com.shivansh.devops_copilot.client;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("smoke")
class GitHubClientLiveTest {

    @Test
    @Disabled("Disabled in automated CI/CD builds to prevent hitting GitHub unauthenticated rate limits. Enable manually for smoke testing.")
    void liveSmokeTestAgainstGitHub() {
        GitHubClient client = new GitHubClient(RestClient.builder());

        List<GitHubRepository> repos = client.getAllByUsername("Shivansh1211");

        assertNotNull(repos, "Repository list should not be null");
        assertFalse(repos.isEmpty(), "Expected at least one public repository for user Shivansh1211");

        System.out.println("=== LIVE SMOKE TEST SUCCESSFUL ===");
        System.out.println("Fetched " + repos.size() + " repositories for Shivansh1211:");
        for (GitHubRepository repo : repos) {
            System.out.printf("- %s [%s] (stars/branch: %s, url: %s)%n",
                    repo.name(), repo.language(), repo.defaultBranch(), repo.htmlUrl());
        }
    }
}
