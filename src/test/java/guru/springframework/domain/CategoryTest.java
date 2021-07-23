package guru.springframework.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by jt on 6/17/17.
 */
public class CategoryTest {

    Category category;

    @BeforeAll
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        String idValue = "4";

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

}