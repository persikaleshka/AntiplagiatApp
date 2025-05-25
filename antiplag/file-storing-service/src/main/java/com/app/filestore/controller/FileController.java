package com.app.filestore.controller;

import com.app.filestore.model.StoredFile;
import com.app.filestore.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@Tag(name = "File API", description = "Управление файлами: загрузка, просмотр")
public class FileController {

    private final FileStorageService service;

    public FileController(FileStorageService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить .txt файл")
    public ResponseEntity<StoredFile> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        StoredFile saved = service.save(file.getOriginalFilename(), content);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    @Operation(summary = "Получить список всех загруженных файлов")
    public ResponseEntity<List<StoredFile>> getAllFiles() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить файл по ID")
    public ResponseEntity<StoredFile> getFileById(@Parameter(description = "ID файла", required = true) @PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.get(id));
    }
}
