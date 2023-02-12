package me.trubnikova.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.trubnikova.cookbook.model.Recipe;
import me.trubnikova.cookbook.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы над объектом РЕЦЕПТ")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Поиск рецепта по номеру",
            description = "Поиск осуществляется только по номеру"
    )
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe result = recipeService.getRecipe(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта по номеру и/или названию",
            description = "Поиск осуществляется только по номеру, только по названию или по обоим параметрам"
    )
    @Parameters(value = {
            @Parameter(name = "id", example = "1"),
            @Parameter(name = "recipe", example = "солянка")
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe result = recipeService.editRecipe(id, recipe);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта по номеру",
            description = "Удаление осуществляется только по номеру"
    )
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllRecipe() {
        recipeService.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }


}
