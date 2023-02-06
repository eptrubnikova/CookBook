package me.trubnikova.cookbook.services.impl;

import me.trubnikova.cookbook.model.Ingredient;
import me.trubnikova.cookbook.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Ingredient getIngredient(int lastId) {
        if (ingredients.containsKey(lastId)) {
            System.out.println(ingredients.get(lastId));
        } return ingredients.get(lastId);
    }
}
