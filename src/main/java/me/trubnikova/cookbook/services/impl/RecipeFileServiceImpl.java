package me.trubnikova.cookbook.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RecipeFileServiceImpl extends FileServiceImpl {

    @Value("${name.of.recipe.data.file}")
    private String dataFileName;

}
