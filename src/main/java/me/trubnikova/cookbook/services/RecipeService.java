package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer lastId);
}
