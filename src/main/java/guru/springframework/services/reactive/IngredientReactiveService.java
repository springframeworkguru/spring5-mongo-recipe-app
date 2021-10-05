package guru.springframework.services.reactive;

import guru.springframework.commands.IngredientCommand;
import reactor.core.publisher.Mono;

/**
 * Created by jt on 6/27/17.
 */
public interface IngredientReactiveService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

    Mono<Void> deleteById(String recipeId, String idToDelete);
}
