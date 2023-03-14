package me.trubnikova.cookbook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.trubnikova.cookbook.model.Recipe;
import me.trubnikova.cookbook.services.Exception.ReadRecipeException;
import me.trubnikova.cookbook.services.Exception.RecipeNotFoundException;
import me.trubnikova.cookbook.services.FileService;
import me.trubnikova.cookbook.services.RecipeService;
import me.trubnikova.cookbook.validate.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    final private FileService fileService;

    private static Map<Long, Recipe> recipes = new LinkedHashMap<>();
    private static long id = 0;

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
    public Recipe getRecipe(Long id) {
        if (!recipes.containsKey(id)) {
            System.out.println("Рецепт с данным номером не найден");
        }
        return recipes.get(id);
    }

    @Override
    public Recipe editRecipe(Long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Long id) {
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
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ReadRecipeException();
        }
    }

    @Override
    public void addRecipesFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
                Recipe recipe = new Recipe(array[0], Integer.valueOf(array[1]), array[2]);
                addRecipe(recipe);
            }

        }
    }

    @Override
    public File createRecipesTxtFile() {
        Path path = fileService.createTempFile("Рецепты");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            for (Recipe recipe : recipes.values()) {
                writer.append(recipe.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new RecipeNotFoundException();
        }
        return path.toFile();
    }

}
