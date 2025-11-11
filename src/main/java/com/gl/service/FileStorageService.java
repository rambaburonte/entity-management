package com.gl.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getUploadDirectory() {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return uploadDir;
    }

    public byte[] readFile(String filename) throws IOException {
        Path filePath = Paths.get(uploadDir, filename);
        return Files.readAllBytes(filePath);
    }

    public boolean fileExists(String filename) {
        Path filePath = Paths.get(uploadDir, filename);
        return Files.exists(filePath);
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = Paths.get(uploadDir, filename);
            Files.deleteIfExists(filePath);
            log.info("File deleted: {}", filename);
        } catch (IOException e) {
            log.error("Error deleting file: {}", filename, e);
        }
    }
}
