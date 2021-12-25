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

    RecipeDTO findCommandById(String id);

    RecipeDTO saveRecipeCommand(RecipeDTO command);

    void deleteById(String idToDelete);
}
