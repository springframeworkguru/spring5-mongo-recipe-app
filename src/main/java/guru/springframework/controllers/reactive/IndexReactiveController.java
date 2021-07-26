package guru.springframework.controllers.reactive;

import guru.springframework.services.RecipeService;
import guru.springframework.services.reactive.RecipeReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 6/1/17.
 */
@Slf4j
@Controller
@Profile("reactive")
public class IndexReactiveController {

    private final RecipeReactiveService recipeService;

    public IndexReactiveController(RecipeReactiveService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");

        model.addAttribute("recipes", recipeService.getRecipes().collectList().block());

        return "index";
    }
}
