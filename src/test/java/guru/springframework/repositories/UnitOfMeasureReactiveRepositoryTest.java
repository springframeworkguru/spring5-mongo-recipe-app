package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String EACH = "Each";
    @Autowired
    UnitOfMeasureReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void testFindByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);

        reactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetchedUoM = reactiveRepository.findByDescription(EACH).block();

        assertNotNull(fetchedUoM);
        assertEquals(EACH, fetchedUoM.getDescription());

    }

    @Test
    public void testSaveUom() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);

        reactiveRepository.save(unitOfMeasure).block();

        Long count = reactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);

    }
}