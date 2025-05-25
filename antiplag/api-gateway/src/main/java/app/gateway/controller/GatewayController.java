package com.app.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import java.util.UUID;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final WebClient webClient;

    @Autowired
    public GatewayController(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFile(@RequestPart("file") FilePart file) {
        return webClient.post()
                .uri("http://file-storing:8081/files")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file))
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/analyze/{fileId}")
    @Operation(summary = "Получить результаты анализа по ID")
    public Mono<String> getAnalysisResult(@Parameter(description = "ID файла", required = true) @PathVariable("fileId") UUID fileId) {
        return webClient.get()
                .uri("http://file-analysis:8082/analysis/" + fileId)
                .retrieve()
                .bodyToMono(String.class);
    }
    
    

    @GetMapping("/files/{fileId}")
    public Mono<String> getFile(@Parameter(description = "UUID файла, который нужно получить", required = true) @PathVariable("fileId") UUID fileId) {
        return webClient.get()
                .uri("http://file-storing:8081/files/" + fileId)
                .retrieve()
                .bodyToMono(String.class);
    }
}
