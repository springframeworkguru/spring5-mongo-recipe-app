package guru.springframework.mapper;

import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.model.UnitOfMeasure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitOfMeasureMapper {
    UnitOfMeasureDTO toDTO(UnitOfMeasure unitOfMeasure);

    UnitOfMeasure toEntity(UnitOfMeasureDTO unitOfMeasureDTO);
}
