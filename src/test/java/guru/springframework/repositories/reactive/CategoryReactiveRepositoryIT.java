package guru.springframework.repositories.reactive;

import guru.springframework.bootstrap.RecipeReactiveBootstrap;
import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Berkson Ximenes
 * Date: 26/07/2021
 * Time: 07:57
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("reactive")
class CategoryReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;
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
    void findByDescription() {
        Optional<Category> category = categoryReactiveRepository.findByDescription("Mexican")
                .blockOptional();

        assertNotNull(category.get().getId());
        assertEquals("Mexican", category.get().getDescription());
    }
}