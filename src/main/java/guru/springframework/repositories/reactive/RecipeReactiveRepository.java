package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by Berkson Ximenes
 * Date: 26/07/2021
 * Time: 07:44
 */
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
