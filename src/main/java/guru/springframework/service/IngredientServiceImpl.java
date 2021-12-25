package guru.springframework.service;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.mapper.IngredientMapper;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by jt on 6/28/17.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientMapper ingredientMapper;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientMapper ingredientMapper,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientMapper = ingredientMapper;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public IngredientDTO findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("recipe id not found. Id: " + recipeId));
//        if (recipe == null) {
//            //todo impl error handling
////             log.error("recipe id not found. Id: " + recipeId);
//            throw new RuntimeException("recipe id not found. Id: " + recipeId);
//        }

        IngredientDTO ingredientDTO = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ingredient id not found: " + ingredientId));
//        if (ingredientDTO == null) {
//            //todo impl error handling
////            log.error("Ingredient id not found: " + ingredientId);
//            throw new RuntimeException("Ingredient id not found: " + ingredientId);
//        }
        return ingredientDTO;
    }

    @Override
    @Transactional
    public IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO) {
        Recipe recipe = recipeRepository.findById(ingredientDTO.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found for id: " + ingredientDTO.getRecipeId()));

//        if (recipe == null) {
//            //todo toss error if not found!
//            log.error("Recipe not found for id: " + ingredientDTO.getRecipeId());
//
//            return new IngredientDTO();
//        } else {
        Ingredient ingredient = recipe
                .getIngredients()
                .stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredientDTO.getId()))
                .findFirst().orElse(null);

        if (ingredient != null) {
            ingredient.setDescription(ingredientDTO.getDescription());
            ingredient.setAmount(ingredientDTO.getAmount());
            ingredient.setUom(unitOfMeasureRepository
                    .findById(ingredientDTO.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
        } else {
            //add new Ingredient
            recipe.addIngredient(ingredientMapper.toEntity(ingredientDTO));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        Ingredient savedIngredient = savedRecipe.getIngredients()
                .stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientDTO.getId()))
                .findFirst()
                .orElse(null);

        return ingredientMapper.toDTO(savedIngredient);
    }
//    }

    @Override
    public void deleteById(String recipeId, String ingredientId) {
        log.debug("Deleting ingredient: " + recipeId + ":" + ingredientId);
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("recipe id not found. Id: " + recipeId));

        log.debug("found recipe");

        Ingredient ingredient = recipe
                .getIngredients()
                .stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ingredient id not found: " + ingredientId));

        log.debug("found Ingredient");
        recipe.getIngredients().remove(ingredient);
        recipeRepository.save(recipe);
    }
}