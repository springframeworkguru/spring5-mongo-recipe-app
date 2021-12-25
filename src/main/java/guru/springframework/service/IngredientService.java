package guru.springframework.service;

import guru.springframework.dto.IngredientDTO;

/**
 * Created by jt on 6/27/17.
 */
public interface IngredientService {

    IngredientDTO findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO);

    void deleteById(String recipeId, String ingredientId);
}
