package me.trubnikova.cookbook.model;

import lombok.Data;
import lombok.Getter;
import me.trubnikova.cookbook.validate.InvalidInputException;
import me.trubnikova.cookbook.validate.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Getter
public class Recipe {

    private String nameRecipe;
    private Integer cookingTime;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cookingMethod;
    private Integer idCount = 0;
    private final Integer id;

    public Recipe(String nameRecipe, int cookingTime, List<Ingredient> ingredients, List<String> cookingMethod) throws InvalidInputException {
        idCount++;
        id = idCount;
        this.nameRecipe = Validate.validateValue(nameRecipe);
        this.cookingTime = Validate.validateValue(cookingTime);
        this.ingredients = (ArrayList<Ingredient>) Objects.requireNonNullElseGet(ingredients, ArrayList::new);
        this.cookingMethod = (ArrayList<String>) Objects.requireNonNullElseGet(cookingMethod, ArrayList::new);
    }


}
