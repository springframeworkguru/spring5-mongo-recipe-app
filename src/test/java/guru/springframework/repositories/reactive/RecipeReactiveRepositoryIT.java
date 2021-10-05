package guru.springframework.repositories.reactive;

import guru.springframework.bootstrap.RecipeReactiveBootstrap;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 26/07/2021
 * Time: 09:07
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeReactiveRepositoryIT {
    public static final String TEST = "test";
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    RecipeReactiveBootstrap recipeReactiveBootstrap;

    @BeforeEach
    void setUp() {
        recipeReactiveBootstrap = new RecipeReactiveBootstrap(categoryReactiveRepository, recipeReactiveRepository,
                unitOfMeasureReactiveRepository);
        recipeReactiveBootstrap.onApplicationEvent(null);
    }


    @Test
    void testSaveRecipe() {
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("onion");
        ingredient.setAmount(BigDecimal.valueOf(5));
        recipe.setDescription(TEST);
        recipe.setDifficulty(Difficulty.EASY);
        Category category = categoryReactiveRepository.findAll().blockFirst();
        recipe.getCategories().add(category);
        recipe.addIngredient(ingredient);


        Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

        assertNotNull(savedRecipe);
        assertEquals(savedRecipe.getDifficulty().toString(), Difficulty.EASY.toString());
        assertEquals(TEST,savedRecipe.getDescription());
        assertEquals(category.getId(), savedRecipe.getCategories().stream().findFirst().get().getId());
    }
}