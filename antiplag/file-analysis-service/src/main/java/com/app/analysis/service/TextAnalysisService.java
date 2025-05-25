package com.app.analysis.service;

import com.app.analysis.model.AnalysisResult;
import com.app.analysis.repository.AnalysisRepository;
import com.app.analysis.util.TextHasher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class TextAnalysisService {

    private final AnalysisRepository repo;
    private final RestTemplate restTemplate;

    public TextAnalysisService(AnalysisRepository repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
    }

    public AnalysisResult analyzeFile(UUID fileId) {
        String url = "http://file-storing:8081/files/" + fileId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String content = response.getBody();

        int paragraphs = content.split("\n\n").length;
        int words = content.split("\\s+").length;
        int chars = content.replaceAll("\\s", "").length();
        String fingerprint = TextHasher.hash(content);

        AnalysisResult result = new AnalysisResult(fileId, paragraphs, words, chars, fingerprint);
        return repo.save(result);
    }

    public List<AnalysisResult> findSimilar(UUID fileId) {
        AnalysisResult target = repo.findByFileId(fileId).orElseThrow();
        return repo.findByFingerprint(target.getFingerprint());
    }

    public AnalysisResult getResult(UUID fileId) {
        return repo.findByFileId(fileId).orElseThrow();
    }
}
