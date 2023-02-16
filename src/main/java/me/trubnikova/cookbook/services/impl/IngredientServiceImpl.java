package me.trubnikova.cookbook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.trubnikova.cookbook.model.Ingredient;
import me.trubnikova.cookbook.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    final private IngredientFileServiceImpl fileService;
    private static TreeMap<Integer, Ingredient> ingredients = new TreeMap<>();

    public IngredientServiceImpl(IngredientFileServiceImpl fileService) {
        this.fileService = fileService;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(ingredient.getId(), ingredient);
        return ingredient;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
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
    public Ingredient getIngredient(int id) {
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
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
