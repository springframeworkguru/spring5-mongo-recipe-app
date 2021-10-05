package guru.springframework.repositories.reactive;

import guru.springframework.bootstrap.RecipeReactiveBootstrap;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 26/07/2021
 * Time: 07:57
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("reactive")
class UnitOfMeasureReactiveRepositoryIT {

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
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureReactiveRepository.findByDescription("Teaspoon")
                .blockOptional();

        assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
    }
}