package guru.springframework.mapper;

import guru.springframework.dto.CategoryDTO;
import guru.springframework.dto.IngredientDTO;
import guru.springframework.dto.NotesDTO;
import guru.springframework.dto.RecipeDTO;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final String RECIPE_ID = "1";
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final String CAT_ID_1 = "1";
    public static final String CAT_ID2 = "2";
    public static final String INGRED_ID_1 = "3";
    public static final String INGRED_ID_2 = "4";
    public static final String NOTES_ID = "4";

    RecipeCommandToRecipe converter;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeDTO()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(RECIPE_ID);
        recipeDTO.setCookTime(COOK_TIME);
        recipeDTO.setPrepTime(PREP_TIME);
        recipeDTO.setDescription(DESCRIPTION);
        recipeDTO.setDifficulty(DIFFICULTY);
        recipeDTO.setDirections(DIRECTIONS);
        recipeDTO.setServings(SERVINGS);
        recipeDTO.setSource(SOURCE);
        recipeDTO.setUrl(URL);

        NotesDTO notes = new NotesDTO();
        notes.setId(NOTES_ID);

        recipeDTO.setNotes(notes);

        CategoryDTO category = new CategoryDTO();
        category.setId(CAT_ID_1);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(CAT_ID2);

        recipeDTO.getCategories().add(category);
        recipeDTO.getCategories().add(category2);

        IngredientDTO ingredient = new IngredientDTO();
        ingredient.setId(INGRED_ID_1);

        IngredientDTO ingredient2 = new IngredientDTO();
        ingredient2.setId(INGRED_ID_2);

        recipeDTO.getIngredients().add(ingredient);
        recipeDTO.getIngredients().add(ingredient2);

        //when
        Recipe recipe  = converter.convert(recipeDTO);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

}