package dev.siqueira.magicfridgeai.service;

import dev.siqueira.magicfridgeai.model.FoodItemModel;
import dev.siqueira.magicfridgeai.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemModel saveItem(FoodItemModel foodItemModel) {
        return foodItemRepository.save(foodItemModel);
    }

    public List<FoodItemModel> findAll() {
        return foodItemRepository.findAll();
    }

    public FoodItemModel updateItem(FoodItemModel foodItemModel) {
        Optional<FoodItemModel> foodItemModelOptional = foodItemRepository.findById(foodItemModel.getId());
        if (foodItemModelOptional.isPresent()) {
            FoodItemModel foodItemSalvo = foodItemModelOptional.get();
            return foodItemRepository.save(foodItemSalvo);
        }
        return null;
    }

    public Boolean deleteItem(FoodItemModel foodItemModel) {
        Optional<FoodItemModel> foodItemModelOptional = foodItemRepository.findById(foodItemModel.getId());
        if (foodItemModelOptional.isPresent()) {
            foodItemRepository.delete(foodItemModelOptional.get());
            return true;
        }
        return false;
    }
}
