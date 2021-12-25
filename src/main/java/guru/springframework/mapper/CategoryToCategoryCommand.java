package guru.springframework.mapper;

import guru.springframework.dto.CategoryDTO;
import guru.springframework.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryDTO> {

    @Synchronized
    @Nullable
    @Override
    public CategoryDTO convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(source.getId());
        categoryDTO.setDescription(source.getDescription());

        return categoryDTO;
    }
}
