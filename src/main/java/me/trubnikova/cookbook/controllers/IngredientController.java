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

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient result = ingredientService.editIngredient(id, ingredient);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
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
