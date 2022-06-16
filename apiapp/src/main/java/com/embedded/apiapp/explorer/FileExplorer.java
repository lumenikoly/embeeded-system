package com.embedded.apiapp.explorer;

import com.embedded.apiapp.dto.FileData;
import org.springframework.web.bind.MissingRequestValueException;

import java.io.IOException;

public interface FileExplorer {

    void createFile(FileData fileData);

    FileData readFile(String fileName, String filePath) throws IOException, MissingRequestValueException;

}
