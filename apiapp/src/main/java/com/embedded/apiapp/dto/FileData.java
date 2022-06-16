package com.embedded.apiapp.dto;

import lombok.Builder;

@Builder
public record FileData(String fileName, String data, String filePath) {

}
