package me.trubnikova.cookbook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.trubnikova.cookbook.model.Ingredient;
import me.trubnikova.cookbook.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    final private IngredientFileServiceImpl fileService;
    private static Map<Long, Ingredient> ingredients = new LinkedHashMap<>();

    public IngredientServiceImpl(IngredientFileServiceImpl fileService) {
        this.fileService = fileService;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put((long) ingredient.getId(), ingredient);
        return ingredient;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    public void addIngredientFromRecipe(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (!ingredients.contains(ingredient)) {

            }
        }
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllIngredient() {
        ingredients = new TreeMap<>();
    }


    @Override
    public Ingredient getIngredient(long id) {
        if (!ingredients.containsKey(id)) {
            System.out.println("Рецепт с данным номером не найден");
        }
        return ingredients.get(id);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
