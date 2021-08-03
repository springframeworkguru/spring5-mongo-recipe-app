package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UnitOfMeasureReactiveRepository extends ReactiveCrudRepository<UnitOfMeasure, String> {

}
