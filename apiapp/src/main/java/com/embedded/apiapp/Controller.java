package com.embedded.apiapp;

import com.embedded.apiapp.explorer.FileExplorer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class Controller {

    private final FileExplorer fileExplorer;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void createFile(@RequestBody FileData fileData) {
        log.info("Получен файл! Данные: {}", fileData);
        fileExplorer.createFile(fileData);
        log.info("Файл успешно записан");
    }

    @GetMapping("/read/{fullFilePath}/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FileData> readFile(@PathVariable("fileName") String fileName, @PathVariable("fullFilePath") String fullFilePath) throws IOException {
        log.info("Получен запрос на чтение файла с именем {}", fileName);
        var file = fileExplorer.readFile(fileName, fullFilePath);
        return ResponseEntity.ok(file);
    }
}
