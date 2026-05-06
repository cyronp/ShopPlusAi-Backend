package com.univille.api.shopplusai.infra.client.gemini;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class Teste {

    private final GeminiClient geminiClient;

    @PostMapping
    public String teste(){
        System.out.println("vou perguntar");
        return geminiClient.perguntar("Ola como vai voce");
    }

}
