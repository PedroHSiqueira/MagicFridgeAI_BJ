package dev.siqueira.magicfridgeai.controller;

import dev.siqueira.magicfridgeai.service.ChatService;
import dev.siqueira.magicfridgeai.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private final ChatService chatService;
    private final FoodItemService foodItemService;

    public RecipeController(FoodItemService foodItemService, ChatService chatService) {
        this.foodItemService = foodItemService;
        this.chatService = chatService;
    }

    @GetMapping("/generate/gemini")
    private ResponseEntity<String> generateRecipeGemini() {
        String response = chatService.generateRecipeGemini(foodItemService.findAll());
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A IA não conseguiu gerar uma resposta para a sua solicitação!");
    }

    @GetMapping("/generate/gpt")
    private Mono<ResponseEntity<String>> generateRecipe() {
        return chatService.generateRecipeGPT(foodItemService.findAll())
                .map(recipe -> ResponseEntity.status(200).body(recipe))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
