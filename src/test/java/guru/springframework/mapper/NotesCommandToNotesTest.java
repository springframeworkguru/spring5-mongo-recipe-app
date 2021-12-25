package guru.springframework.mapper;

import guru.springframework.dto.NotesDTO;
import guru.springframework.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final String ID_VALUE = "1";
    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesDTO()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesDTO notesDTO = new NotesDTO();
        notesDTO.setId(ID_VALUE);
        notesDTO.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesDTO);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}