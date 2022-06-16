package com.embedded.apiapp.dto;

import lombok.Builder;

@Builder
public record FileRequest(String fileName, String filePath) {
}
