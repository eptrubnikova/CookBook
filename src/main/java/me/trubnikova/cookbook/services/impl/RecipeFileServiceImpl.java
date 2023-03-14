package me.trubnikova.cookbook.services.impl;

import me.trubnikova.cookbook.model.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


@Service
public class RecipeFileServiceImpl extends FileServiceImpl {

    @Value("${name.of.recipe.data.file}")
    private String dataFileName;


}
