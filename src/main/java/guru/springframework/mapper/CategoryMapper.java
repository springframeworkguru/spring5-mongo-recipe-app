package guru.springframework.mapper;

import guru.springframework.dto.CategoryDTO;
import guru.springframework.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
