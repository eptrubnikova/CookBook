package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Ingredient;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);

    void deleteAllIngredient();

    Ingredient getIngredient(int id);
}
