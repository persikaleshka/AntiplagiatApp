package com.app.analysis.repository;

import com.app.analysis.model.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnalysisRepository extends JpaRepository<AnalysisResult, UUID> {
    Optional<AnalysisResult> findByFileId(UUID fileId);
    List<AnalysisResult> findByFingerprint(String fingerprint);
}
