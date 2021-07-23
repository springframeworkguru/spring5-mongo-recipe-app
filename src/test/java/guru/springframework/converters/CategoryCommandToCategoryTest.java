package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandToCategoryTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory conveter;

    @BeforeAll
    public void setUp() throws Exception {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}