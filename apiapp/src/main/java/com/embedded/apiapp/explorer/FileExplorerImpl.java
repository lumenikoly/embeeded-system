package com.embedded.apiapp.explorer;

import com.embedded.apiapp.dto.FileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileExistsException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileExplorerImpl implements FileExplorer {

    private static final String EXTENSION = ".txt";

    @Override
    public void createFile(FileData fileData) {
        try {
            if (fileData.fileName().isBlank() || fileData.data().isBlank() || fileData.filePath().isBlank()) {
                throw new MissingRequestValueException("Данные не заполнены!");
            }
            var fileName = fileData.fileName();
            var filePath = Path.of(fileData.filePath().concat("/").concat(fileName).concat(EXTENSION));
            if (Files.exists(filePath)) {
                log.error("Ошибка! Файл уже существует!");
                throw new FileExistsException();
            }
            Files.write(filePath, Collections.singleton(fileData.data()));
            log.info("Файл записан, путь: {}", filePath);
        } catch (Exception e) {
            log.error("Произошла ошибка при записи файла!", e);
        }
    }

    @Override
    public FileData readFile(String fileName, String filePath) throws IOException, MissingRequestValueException {
        try {
            if (fileName.isBlank() || filePath.isBlank()) {
                throw new MissingRequestValueException("Данные не заполнены!");
            }
            var filePathFull = Path.of(filePath.concat("/").concat(fileName).concat(EXTENSION));
            if (Files.notExists(filePathFull)) {
                log.error("Ошибка! Файл не найден!");
                throw new FileNotFoundException();
            }
            String data = new String(Files.readAllBytes(filePathFull));
            log.info("Файл успешно прочитан");
            return FileData.builder()
                    .fileName(fileName)
                    .data(data)
                    .filePath(filePathFull.toString())
                    .build();
        } catch (Exception e) {
            log.error("Произошла ошибка при чтении файла!", e);
            throw e;
        }
    }
}
