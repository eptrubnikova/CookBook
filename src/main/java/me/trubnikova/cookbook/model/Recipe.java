package me.trubnikova.cookbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.trubnikova.cookbook.validate.InvalidInputException;
import me.trubnikova.cookbook.validate.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String nameRecipe;
    private Integer cookingTime;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cookingMethod;

    public Recipe(String s, Integer valueOf, String s1) {
    }

}
