package dev.siqueira.magicfridgeai.controller;

import dev.siqueira.magicfridgeai.model.FoodItemModel;
import dev.siqueira.magicfridgeai.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    public ResponseEntity<FoodItemModel> createItem(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel foodItemSalvo = foodItemService.saveItem(foodItemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemSalvo);
    }

    public ResponseEntity<List<FoodItemModel>> findAllItens() {
        return ResponseEntity.status(HttpStatus.OK).body(foodItemService.findAll());
    }

    public ResponseEntity<FoodItemModel> updateItem(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel foodItemSalvo = foodItemService.updateItem(foodItemModel);
        if (foodItemSalvo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foodItemModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<FoodItemModel> deleteItem(@RequestBody FoodItemModel foodItemModel) {
        Boolean isDeleted = foodItemService.deleteItem(foodItemModel);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(foodItemModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
