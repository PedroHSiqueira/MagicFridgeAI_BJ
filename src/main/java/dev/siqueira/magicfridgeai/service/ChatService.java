package dev.siqueira.magicfridgeai.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import dev.siqueira.magicfridgeai.model.FoodItemModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final WebClient webClient;
    private final String apiKey = System.getenv("GOOGLE_API_KEY");

    public ChatService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String generateRecipeGemini(List<FoodItemModel> foodItemModels) {
        Client client = new Client();

        String alimentos = foodItemModels.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, validade: %s", item.getName(), item.getCategory(), item.getQuantity(), item.getValidate()))
                .collect(Collectors.joining("\n"));

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "Agora você é um chef de cozinha e ira me receitar receitas com base nos Seguintes alimento disponiveis: " + alimentos,
                        null);

        return response.text();
    }

    public Mono<String> generateRecipeGPT(List<FoodItemModel> foodItemModels) {
        String alimentos = foodItemModels.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, validade: %s", item.getName(), item.getCategory(), item.getQuantity(), item.getValidate()))
                .collect(Collectors.joining("\n"));


        String prompt = "Agora você é um chef de cozinha e ira me receitar receitas com estes alimentos: " + alimentos;
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-5",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um assistente que cria receitas"),
                        Map.of("role", "user", "content", prompt)
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()){
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma Receita foi gerada!";
                });
    }
}
