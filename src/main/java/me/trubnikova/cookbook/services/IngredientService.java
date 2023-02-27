package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Ingredient;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient editIngredient(long id, Ingredient ingredient);

    boolean deleteIngredient(long id);

    void deleteAllIngredient();

    Ingredient getIngredient(long id);
}
