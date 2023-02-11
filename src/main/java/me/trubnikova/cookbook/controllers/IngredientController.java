package me.trubnikova.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.trubnikova.cookbook.model.Ingredient;
import me.trubnikova.cookbook.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы над объектом ИНГРЕДИЕНТ")
public class IngredientController {

    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok("ID ингредиента: " + ingredientService.addIngredient(ingredient));
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Поиск ингредиента по номеру",
            description = "Поиск осуществляется только по номеру"
    )
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient result = ingredientService.getIngredient(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Корректировка ингредиента по номеру и/или по названию",
            description = "Поиск осуществляется только по номеру/ по названию или по обоим параметрам"
    )
    @Parameters(value = {
            @Parameter(name = "id", example = "1"),
            @Parameter(name = "ingredient", example = "мука")
    })
    public ResponseEntity<Ingredient> editIngredient(@PathVariable(required = false) int id, @RequestBody(required = false) Ingredient ingredient) {
        Ingredient result = ingredientService.editIngredient(id, ingredient);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента по номеру",
            description = "Удаление осуществляется только по номеру"
    )
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllIngredient() {
        ingredientService.deleteAllIngredient();
        return ResponseEntity.ok().build();
    }
}
