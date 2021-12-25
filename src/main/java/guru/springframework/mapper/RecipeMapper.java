package guru.springframework.mapper;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.model.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, NotesMapper.class, IngredientMapper.class})
public interface RecipeMapper {
    RecipeDTO toDTO(Recipe recipe);

    Recipe toEntity(RecipeDTO recipeDTO);
}
