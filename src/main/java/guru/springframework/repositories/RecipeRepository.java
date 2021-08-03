package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jt on 6/13/17.
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
