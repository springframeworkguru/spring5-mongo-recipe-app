package guru.springframework.mapper;

import guru.springframework.dto.NotesDTO;
import guru.springframework.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesDTO>{

    @Synchronized
    @Nullable
    @Override
    public NotesDTO convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesDTO notesDTO = new NotesDTO();
        notesDTO.setId(source.getId());
        notesDTO.setRecipeNotes(source.getRecipeNotes());
        return notesDTO;
    }
}
