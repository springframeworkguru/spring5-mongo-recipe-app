package guru.springframework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Category {
    @Id
    private String id;
    private String description;

    @DBRef
    private Set<Recipe> recipes;
}
