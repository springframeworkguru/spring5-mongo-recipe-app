package guru.springframework.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
public class Notes {
    private String id;
    private String recipeNotes;
    private Recipe recipe;
}
