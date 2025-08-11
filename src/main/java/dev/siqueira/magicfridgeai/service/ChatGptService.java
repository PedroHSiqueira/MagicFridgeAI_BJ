package dev.siqueira.magicfridgeai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("OPENAI_API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe() {
        String prompt = "Agora você é um chef de cozinha e ira me receitar receitas com base nos ingredientes que vou te enviar";
    }
}
