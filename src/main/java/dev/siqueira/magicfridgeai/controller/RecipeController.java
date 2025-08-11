package dev.siqueira.magicfridgeai.controller;

import dev.siqueira.magicfridgeai.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    private Mono<ResponseEntity<String>> generateRecipe(){
        return chatGptService.generateRecipe();
    }
}
