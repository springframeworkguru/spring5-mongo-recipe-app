package guru.springframework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {
    private String id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDTO uom;
    private String recipeId;
}
