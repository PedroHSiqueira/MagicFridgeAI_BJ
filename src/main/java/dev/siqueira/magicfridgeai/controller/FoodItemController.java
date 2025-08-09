package dev.siqueira.magicfridgeai.controller;

import dev.siqueira.magicfridgeai.model.FoodItemModel;
import dev.siqueira.magicfridgeai.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItemModel> createItem(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel foodItemSalvo = foodItemService.saveItem(foodItemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemSalvo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodItemModel>> findAllItens() {
        return ResponseEntity.status(HttpStatus.OK).body(foodItemService.findAll());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        FoodItemModel foodItem = foodItemService.findById(id);
        if (foodItem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foodItem);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entidade não encontrada!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id) {
        FoodItemModel foodItemSalvo = foodItemService.updateItem(id);
        if (foodItemSalvo != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Entidade de ID:" + id + " Foi Atualizada!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entidade não encontrada!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        Boolean isDeleted = foodItemService.deleteItem(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Entidade de ID:" + id + " Foi Deletada!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não doi Possivel Deletar a Entidade!");
    }
}
