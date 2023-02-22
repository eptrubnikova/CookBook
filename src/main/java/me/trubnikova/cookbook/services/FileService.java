package me.trubnikova.cookbook.services;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();
}
