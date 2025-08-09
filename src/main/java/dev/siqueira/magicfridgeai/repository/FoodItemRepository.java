package dev.siqueira.magicfridgeai.repository;

import dev.siqueira.magicfridgeai.model.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemModel, Long> {
}
