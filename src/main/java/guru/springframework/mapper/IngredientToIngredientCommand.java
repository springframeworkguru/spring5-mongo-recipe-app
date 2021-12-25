package guru.springframework.mapper;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientDTO> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDTO convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        if (ingredient.getRecipe() != null) {
            ingredientDTO.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setUom(uomConverter.convert(ingredient.getUom()));
        return ingredientDTO;
    }
}
