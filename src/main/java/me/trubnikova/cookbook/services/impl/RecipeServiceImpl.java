package me.trubnikova.cookbook.services.impl;

import me.trubnikova.cookbook.model.Recipe;
import me.trubnikova.cookbook.services.RecipeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipes = new LinkedHashMap<>();
    private static Integer lastId = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(lastId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer lastId) {
        if (recipes.containsKey(lastId)) {
        }
        return recipes.get(lastId);
    }
}
