package guru.springframework.services.reactive;

import guru.springframework.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureReactiveService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
