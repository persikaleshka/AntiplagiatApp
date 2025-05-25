package com.app.analysis.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AnalysisResult {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID fileId;
    private int paragraphCount;
    private int wordCount;
    private int characterCount;
    private String fingerprint;
    private LocalDateTime analyzedAt;

    public AnalysisResult() {}

    public AnalysisResult(UUID fileId, int paragraphs, int words, int chars, String fingerprint) {
        this.fileId = fileId;
        this.paragraphCount = paragraphs;
        this.wordCount = words;
        this.characterCount = chars;
        this.fingerprint = fingerprint;
        this.analyzedAt = LocalDateTime.now();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getFileId() {
        return fileId;
    }

    public int getParagraphCount() {
        return paragraphCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public void setParagraphCount(int paragraphCount) {
        this.paragraphCount = paragraphCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setCharacterCount(int characterCount) {
        this.characterCount = characterCount;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}
