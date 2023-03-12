package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Long lastId);

    Recipe editRecipe(Long id, Recipe recipe);

    boolean deleteRecipe(Long id);

    void deleteAllRecipe();
}
