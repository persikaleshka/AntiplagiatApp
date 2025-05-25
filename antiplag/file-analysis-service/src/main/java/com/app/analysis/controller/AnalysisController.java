package com.app.analysis.controller;

import com.app.analysis.model.AnalysisResult;
import com.app.analysis.service.TextAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/analysis")
@Tag(name = "Analysis API", description = "Анализ файлов и поиск дубликатов")
public class AnalysisController {

    private final TextAnalysisService service;

    public AnalysisController(TextAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/{fileId}")
    @Operation(summary = "Запустить анализ файла по ID")
    public AnalysisResult analyze(@Parameter(description = "ID файла для анализа", required = true) @PathVariable("fileId") UUID fileId) {
        return service.analyzeFile(fileId);
    }

    @GetMapping("/{fileId}")
    @Operation(summary = "Получить результат анализа файла")
    public AnalysisResult getAnalysis(@Parameter(description = "ID файла", required = true) @PathVariable("fileId") UUID fileId) {
        return service.getResult(fileId);
    }

    @GetMapping("/similar/{fileId}")
    @Operation(summary = "Найти файлы с таким же содержанием (антиплагиат)")
    public List<AnalysisResult> getSimilar(@Parameter(description = "ID файла для поиска похожих", required = true) @PathVariable("fileId") UUID fileId) {
        return service.findSimilar(fileId);
    }
}
