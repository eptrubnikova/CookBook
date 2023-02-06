package me.trubnikova.cookbook.services;

import me.trubnikova.cookbook.model.Ingredient;

public interface IngredientService {

    int addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int lastId);
}
