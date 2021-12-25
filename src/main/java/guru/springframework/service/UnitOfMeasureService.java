package guru.springframework.service;

import guru.springframework.dto.UnitOfMeasureDTO;

import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureDTO> listAllUoms();
}
