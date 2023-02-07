package me.trubnikova.cookbook.services.impl;

import me.trubnikova.cookbook.model.Ingredient;
import me.trubnikova.cookbook.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();

    @Override
    public int addIngredient(Ingredient ingredient) {
        ingredients.put(ingredient.getId(), ingredient);
        return ingredient.getId();
    }

    @Override
    public Ingredient getIngredient(int id) {
        if (!ingredients.containsKey(id)) {
            System.out.println("Рецепт с данным номером не найден");
        } return ingredients.get(id);
    }
}
