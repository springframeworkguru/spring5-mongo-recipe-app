package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Berkson Ximenes
 * Date: 26/07/2021
 * Time: 07:44
 */
@Repository
@Profile("reactive")
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
