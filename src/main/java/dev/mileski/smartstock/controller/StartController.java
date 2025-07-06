package dev.mileski.smartstock.controller;

import dev.mileski.smartstock.controller.dto.StartDto;
import dev.mileski.smartstock.service.SmartStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class StartController {

    private final SmartStockService smartStockService;

    public StartController(SmartStockService smartStockService) {
        this.smartStockService = smartStockService;
    }

    @PostMapping(path = "/start")
    public ResponseEntity<Void> start(@RequestBody StartDto dto) {

        try {
            CompletableFuture.runAsync(() -> {
                smartStockService.start(dto.reportPath());
            });
        }
        catch (Exception e) {
            // Handle exceptions appropriately, e.g., log the error or return an error response
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }

}

