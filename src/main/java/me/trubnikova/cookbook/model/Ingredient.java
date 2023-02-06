package me.trubnikova.cookbook.model;

import lombok.Data;
import lombok.Getter;
import me.trubnikova.cookbook.validate.InvalidInputException;
import me.trubnikova.cookbook.validate.Validate;

@Data
@Getter
public class Ingredient {

    private static String nameIngredient;
    private static double count;
    private static String unit;
    private static int idCount = 0;
    private final int id;

    public Ingredient(String nameIngredient, double count, String unit) throws InvalidInputException {
        id = ++idCount;
        Ingredient.nameIngredient = Validate.validateValue(nameIngredient);
        Ingredient.unit = Validate.validateValue(unit);
        Ingredient.count = Validate.validateValue(count);

    }
}
