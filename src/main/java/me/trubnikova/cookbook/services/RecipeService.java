package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer lastId);

    Recipe editRecipe(Integer id, Recipe recipe);

    boolean deleteRecipe(Integer id);

    void deleteAllRecipe();
}
