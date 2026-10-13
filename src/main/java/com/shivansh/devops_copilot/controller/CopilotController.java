package com.shivansh.devops_copilot.controller;

import com.shivansh.devops_copilot.tools.GithubTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/copilot")
public class CopilotController {

    private final ChatClient chatClient;

    public CopilotController(ChatClient.Builder chatClientBuilder, GithubTools githubTools)
    {
        this.chatClient=chatClientBuilder
                .defaultTools(githubTools)
                .build();
    }
   @GetMapping("/ask")
    public String ask(@RequestParam(name="message") String message)
   {
       return chatClient.prompt(message)
               .call()
               .content();
   }

}
