package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
public class Category {
    private String id;
    private String description;
    private Set<Recipe> recipes;
}
