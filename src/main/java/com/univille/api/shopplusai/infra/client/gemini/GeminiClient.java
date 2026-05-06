
package com.univille.api.shopplusai.infra.client.gemini;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final ChatClient chatClient;

    public String perguntar(String pergunta){

        return chatClient.prompt()
                .user(pergunta)
                .call()
                .content();
    }

}
