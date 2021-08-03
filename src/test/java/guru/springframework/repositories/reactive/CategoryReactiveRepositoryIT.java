package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryReactiveRepositoryIT {

    @Container
    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:5.0.1");

    public static final String EACH = "Each";

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @BeforeAll
    static void setUpAll() {
        MONGO_DB_CONTAINER.start();
    }

    @AfterAll
    static void tearDownAll() {
        if (!MONGO_DB_CONTAINER.isShouldBeReused()) {
            MONGO_DB_CONTAINER.stop();
        }
    }

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setDescription(EACH);

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(1L, count);
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription(EACH);

        categoryReactiveRepository.save(category).block();

        Category categoryFoo = categoryReactiveRepository.findCategoryByDescription(EACH).block();

        assertNotNull(categoryFoo.getId());
        assertEquals(category, categoryFoo);
    }
}