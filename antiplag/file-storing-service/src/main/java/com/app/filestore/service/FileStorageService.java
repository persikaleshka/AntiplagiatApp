package com.app.filestore.service;

import com.app.filestore.model.StoredFile;
import com.app.filestore.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    private final FileRepository repository;

    public FileStorageService(FileRepository repository) {
        this.repository = repository;
    }

    public StoredFile save(String filename, String content) {
        return repository.save(new StoredFile(filename, content));
    }

    public StoredFile get(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public List<StoredFile> getAll() {
        return repository.findAll();
    }
}
