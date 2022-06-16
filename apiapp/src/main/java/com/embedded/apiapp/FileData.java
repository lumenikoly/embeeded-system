package com.embedded.apiapp;

import lombok.Builder;

@Builder
public record FileData(String fileName, String data, String filePath) {

}
