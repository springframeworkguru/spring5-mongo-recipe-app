package guru.springframework.mapper;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnitOfMeasureMapper.class})
public interface IngredientMapper {
    @Mapping(target = "recipeId", source = "recipe.id")
    IngredientDTO toDTO(Ingredient ingredient);

    @Mapping(target = "recipe.id", source = "recipeId")
    Ingredient toEntity(IngredientDTO ingredientDTO);
}
