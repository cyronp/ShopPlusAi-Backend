
package com.univille.api.shopplusai.infra.client.gemini;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final ChatClient chatClient;

    public String chat(String promptSystem, String promptUser){

        return chatClient.prompt()
                .system(promptSystem)
                .user(promptUser)
                .call()
                .content();
    }

}
