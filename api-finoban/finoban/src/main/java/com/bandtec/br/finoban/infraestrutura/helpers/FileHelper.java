package com.bandtec.br.finoban.infraestrutura.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileHelper {

    public static Path getFilePath(String fileName) {
        return Paths.get(fileName);
    }

    public static File getFile(String fileName) {
        return getFilePath(fileName).toFile();
    }

    public static BasicFileAttributes getAttributes(Path file) throws IOException
    {
        return Files.readAttributes(file, BasicFileAttributes.class);
    }
}
