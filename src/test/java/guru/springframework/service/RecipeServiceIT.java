package guru.springframework.service;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.mapper.RecipeCommandToRecipe;
import guru.springframework.mapper.RecipeToRecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


/**
 * Created by jt on 6/21/17.
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeDTO testRecipeDTO = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeDTO.setDescription(NEW_DESCRIPTION);
        RecipeDTO savedRecipeDTO = recipeService.saveRecipeCommand(testRecipeDTO);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeDTO.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeDTO.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeDTO.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeDTO.getIngredients().size());
    }
}
