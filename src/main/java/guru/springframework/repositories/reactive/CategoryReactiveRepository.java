package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by Berkson Ximenes
 * Date: 26/07/2021
 * Time: 07:43
 */
@Repository
@Profile("reactive")
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByDescription(String description);
}
