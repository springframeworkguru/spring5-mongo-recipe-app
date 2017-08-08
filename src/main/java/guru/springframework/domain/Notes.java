package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
public class Notes {

    private String id;
    private Recipe recipe;
    private String recipeNotes;

}
