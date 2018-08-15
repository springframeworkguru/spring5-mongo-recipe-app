package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    private static String american = "American";

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() {
        Category category = new Category();
        category.setDescription(american);

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }
    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription(american);

        categoryReactiveRepository.save(category).block();

        Category loadedCategory = categoryReactiveRepository.findByDescription(american).block();

        assertNotNull(loadedCategory);
        assertEquals(category.getId(), loadedCategory.getId());
    }
}