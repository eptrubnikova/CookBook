package me.trubnikova.cookbook.services.impl;

import me.trubnikova.cookbook.model.Recipe;
import me.trubnikova.cookbook.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipes = new LinkedHashMap<>();
    private static Integer id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        if (!recipes.containsKey(id)) {
            System.out.println("Рецепт с данным номером не найден");
        }
        return recipes.get(id);
    }
}
