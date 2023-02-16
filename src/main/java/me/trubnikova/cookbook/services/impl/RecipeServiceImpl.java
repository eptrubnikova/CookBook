package me.trubnikova.cookbook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.trubnikova.cookbook.model.Recipe;
import me.trubnikova.cookbook.services.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    final private RecipeFileServiceImpl fileService;

    private static TreeMap<Integer, Recipe> recipes = new TreeMap<>();
    private static Integer id = 0;

    public RecipeServiceImpl(RecipeFileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        if (!recipes.containsKey(id)) {
            System.out.println("Рецепт с данным номером не найден");
        }
        return recipes.get(id);
    }

    @Override
    public Recipe editRecipe(Integer id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Integer id) {
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllRecipe() {
        recipes = new TreeMap<>();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
