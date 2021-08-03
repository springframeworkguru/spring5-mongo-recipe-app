package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveCrudRepository<Category, String> {

    Mono<Category> findCategoryByDescription(String description);

}
