package com.shivansh.devops_copilot.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(GitHubClient.class)
class GitHubClientTest {

    @Autowired
    private RestClient.Builder restClientBuilder;

    private MockRestServiceServer server;
    private GitHubClient githubClient;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.bindTo(restClientBuilder).build();
        githubClient = new GitHubClient(restClientBuilder);
    }

    @Test
    void shouldReturnRepos() {
        
        String jsonResponse = """
                [
                  {
                    "id": 101,
                    "name": "DevOps-Copilot",
                    "language": "Java",
                    "description": "An AI DevOps assistant",
                    "private": false,
                    "html_url": "https://github.com/Shivansh1211/DevOps-Copilot",
                    "default_branch": "main"
                  }
                ]
                """;

        server.expect(requestTo(
                        "https://api.github.com/users/Shivansh1211/repos"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        jsonResponse,
                        MediaType.APPLICATION_JSON));

        // Act: Call the method we're testing
        List<GitHubRepository> repos =
                githubClient.getAllByUsername("Shivansh1211");

        // Assert: Check the returned data
        assertEquals(1, repos.size());
        assertEquals("DevOps-Copilot", repos.get(0).name());
        assertEquals(101L, repos.get(0).id());
        assertEquals("Java", repos.get(0).language());
        assertFalse(repos.get(0).privateRepo());

        // Verify that all expected mock requests were made
        server.verify();
    }
}