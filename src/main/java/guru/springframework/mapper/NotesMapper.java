package guru.springframework.mapper;

import guru.springframework.dto.NotesDTO;
import guru.springframework.model.Notes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotesMapper {
    NotesDTO toDTO(Notes notes);

    Notes toEntity(NotesDTO notesDTO);
}
