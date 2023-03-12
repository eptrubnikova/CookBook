package me.trubnikova.cookbook.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class IngredientFileServiceImpl extends FileServiceImpl {

    @Value("${name.of.ingredient.data.file}")
    private String dataFileName;
}
