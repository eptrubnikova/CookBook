package me.trubnikova.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.trubnikova.cookbook.services.RecipeService;
import me.trubnikova.cookbook.services.impl.RecipeFileServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/recipe/files")
public class RecipeFileController {

    private final RecipeFileServiceImpl fileService;

    private final RecipeService recipeService;

    public RecipeFileController(RecipeFileServiceImpl fileService, RecipeService recipeService) {
        this.fileService = fileService;
        this.recipeService = recipeService;
    }


    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"TransactionLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileService.cleanDataFile();
        File dataFile = fileService.getDataFile();

        try {
            FileOutputStream fos = new FileOutputStream(dataFile);
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/export/recipes/txt")
    @Operation(summary = "Скачивание рецептов в формате txt",
            description = "можно скачать рецепты файлом txt")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "все хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "400", description = "есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity<InputStreamResource> downloadRecipeTxtFile(){
        File downloadedFile = recipeService.createRecipesTxtFile();
        try {
            InputStreamResource stream = new InputStreamResource(new FileInputStream(downloadedFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(downloadedFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"")
                    .body(stream);
        } catch (IOException e) {
            return ResponseEntity.noContent().build();
        }
    }
}
