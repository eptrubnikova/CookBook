package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Long lastId);

    Recipe editRecipe(Long id, Recipe recipe);

    boolean deleteRecipe(Long id);

    void deleteAllRecipe();

    File createRecipesTxtFile();

    void addRecipesFromInputStream(InputStream inputStream) throws IOException;
}
