package me.trubnikova.cookbook.controllers;

import me.trubnikova.cookbook.model.Ingredient;
import me.trubnikova.cookbook.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
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
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient result = ingredientService.getIngredient(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
