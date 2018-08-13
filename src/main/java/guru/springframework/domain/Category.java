package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Document
public class Category {

    @Id
    private String id = UUID.randomUUID().toString();
    private String description;
    private Set<Recipe> recipes;

    public Category() {
    }

    public Category(String id, String description, Set<Recipe> recipes) {
        this.id = id;
        this.description = description;
        this.recipes = recipes;
    }

    public Category(String description) {
        this.description = description;
    }
}
