package guru.springframework.mapper;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by jt on 6/21/17.
 */
public class IngredientToIngredientCommandTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String UOM_ID = "2";
    public static final String ID_VALUE = "1";


    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);
        //when
        IngredientDTO ingredientDTO = converter.convert(ingredient);
        //then
        assertNull(ingredientDTO.getUom());
        assertEquals(ID_VALUE, ingredientDTO.getId());
        assertEquals(AMOUNT, ingredientDTO.getAmount());
        assertEquals(DESCRIPTION, ingredientDTO.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUom(uom);
        //when
        IngredientDTO ingredientDTO = converter.convert(ingredient);
        //then
        assertEquals(ID_VALUE, ingredientDTO.getId());
        assertNotNull(ingredientDTO.getUom());
        assertEquals(UOM_ID, ingredientDTO.getUom().getId());
        // assertEquals(RECIPE, ingredientDTO.get);
        assertEquals(AMOUNT, ingredientDTO.getAmount());
        assertEquals(DESCRIPTION, ingredientDTO.getDescription());
    }
}