package me.trubnikova.cookbook.services.impl;

import me.trubnikova.cookbook.services.Exception.ReadIngredientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class IngredientFileServiceImpl extends FileServiceImpl {

    @Value("${name.of.ingredient.data.file}")
    private String dataFileName;

    @Value("${path.to.data.file}")
    private String dataFilePath;

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new ReadIngredientException();
        }
    }
}
