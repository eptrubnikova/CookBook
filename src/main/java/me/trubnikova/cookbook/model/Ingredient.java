package me.trubnikova.cookbook.model;

import lombok.Data;
import lombok.Getter;
import me.trubnikova.cookbook.validate.InvalidInputException;
import me.trubnikova.cookbook.validate.Validate;

@Data
@Getter
public class Ingredient {

    private String nameIngredient;
    private double count;
    private String unit;
    private static int idCount = 0;
    private final int id;

    public Ingredient(String nameIngredient, double count, String unit) throws InvalidInputException {
        id = ++idCount;
        this.nameIngredient = Validate.validateValue(nameIngredient);
        this.unit = Validate.validateValue(unit);
        this.count = Validate.validateValue(count);

    }
}
