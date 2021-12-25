package guru.springframework.service;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.model.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeDTO findDTOById(String id);

    RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO);

    void deleteById(String id);
}
