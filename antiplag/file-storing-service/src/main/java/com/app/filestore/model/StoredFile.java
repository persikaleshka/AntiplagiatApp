package com.app.filestore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class StoredFile {

    @Id
    @GeneratedValue
    private UUID id;

    private String filename;

    @Lob
    private String content;

    private LocalDateTime uploadTime;

    public StoredFile() {}

    public StoredFile(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.uploadTime = LocalDateTime.now();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}