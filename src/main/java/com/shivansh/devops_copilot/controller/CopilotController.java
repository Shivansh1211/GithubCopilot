package com.shivansh.devops_copilot.controller;

import com.shivansh.devops_copilot.tools.GithubTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/copilot")
public class CopilotController {

    private final ChatClient chatClient;

    public CopilotController(ChatClient.Builder chatClientBuilder,
                             GithubTools githubTools,
                             ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder
                .defaultTools(githubTools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    @GetMapping("/ask")
    public String ask(
            @RequestParam(name = "message") String message,
            @RequestParam(name = "conversationId", defaultValue = "default") String conversationId) {
        return chatClient.prompt(message)
                .advisors(advisorSpec -> advisorSpec.param("chat_memory_conversation_id", conversationId))
                .call()
                .content();
    }
}
