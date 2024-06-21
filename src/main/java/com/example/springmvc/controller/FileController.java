package com.example.springmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        logger.info("Request to serve file: {}", filename);

        if (filename == null || filename.trim().isEmpty()) {
            logger.error("Filename is null or empty");
            throw new RuntimeException("Filename is required");
        }

        try {
            Path file = Paths.get(uploadDir).resolve(filename).normalize();
            logger.info("Resolved file path: {}", file);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = "application/octet-stream"; // Fallback to a binary type if MIME type cannot be determined
                }
                logger.info("File exists and is readable: {}, MIME type: {}", filename, contentType);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                logger.error("Could not read the file: {}", filename);
                throw new IOException("Could not read the file: " + filename);
            }
        } catch (MalformedURLException e) {
            logger.error("Malformed URL for file: {}", filename, e);
            throw new RuntimeException("Could not read the file due to malformed URL: " + filename, e);
        } catch (IOException e) {
            logger.error("IOException for file: {}", filename, e);
            throw new RuntimeException("Could not read the file: " + filename, e);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
