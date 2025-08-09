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

    public FoodItemModel findById(Long id){
        return foodItemRepository.findById(id).orElse(null);
    }

    public FoodItemModel updateItem(Long id) {
        Optional<FoodItemModel> foodItemModelOptional = foodItemRepository.findById(id);
        if (foodItemModelOptional.isPresent()) {
            FoodItemModel foodItemSalvo = foodItemModelOptional.get();
            foodItemSalvo.setId(id);
            return foodItemRepository.save(foodItemSalvo);
        }
        return null;
    }

    public Boolean deleteItem(Long id) {
        Optional<FoodItemModel> foodItemModelOptional = foodItemRepository.findById(id);
        if (foodItemModelOptional.isPresent()) {
            foodItemRepository.delete(foodItemModelOptional.get());
            return true;
        }
        return false;
    }
}
