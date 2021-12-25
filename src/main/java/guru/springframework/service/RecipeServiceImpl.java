package guru.springframework.service;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.mapper.RecipeMapper;
import guru.springframework.model.Recipe;
import guru.springframework.exception.NotFoundException;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    @Transactional
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    @Transactional
    public Recipe findById(String id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe Not Found. For ID value: " + id));
    }

    @Override
    @Transactional
    public RecipeDTO findDTOById(String id) {
        return recipeMapper.toDTO(findById(id));
    }

    @Override
    @Transactional
    public RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());

        return recipeMapper.toDTO(savedRecipe);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }
}