package guru.springframework.service;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.mapper.RecipeMapper;
import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by jt on 6/21/17.
 */
@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeMapper recipeMapper;


    @Transactional
    @Test
    void testSaveOfDescription() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeDTO testRecipeDTO = recipeMapper.toDTO(testRecipe);

        //when
        testRecipeDTO.setDescription(NEW_DESCRIPTION);
        RecipeDTO savedRecipeDTO = recipeService.saveRecipeDTO(testRecipeDTO);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeDTO.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeDTO.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeDTO.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeDTO.getIngredients().size());
    }
}
