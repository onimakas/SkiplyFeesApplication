package com.skiply.fees_collection.controllers;

import com.skiply.fees_collection.dtos.FeesCollectionDto;
import com.skiply.fees_collection.dtos.FeesReceiptDto;
import com.skiply.fees_collection.services.FeesCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fees")
@Tag(name = "Fees Collection", description = "Fees Collection APIs")
public class FeesCollectionController {
    private final FeesCollectionService feesCollectionService;

    @Autowired
    public FeesCollectionController(FeesCollectionService feesCollectionService) {
        this.feesCollectionService = feesCollectionService;
    }

    @GetMapping("/receipts/{id}")
    @Operation(summary = "Fetch fees receipt by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "receipt not found")
    })
    public ResponseEntity<FeesReceiptDto> getFeesReceipt(@PathVariable String id) {
        try {
            FeesReceiptDto feesReceipt = feesCollectionService.getFeesReceiptById(id);
            return ResponseEntity.ok(feesReceipt);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/collect")
    @Operation(summary = "Collect fees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    public ResponseEntity<FeesReceiptDto> collectFees(@RequestBody FeesCollectionDto fees) {
        FeesReceiptDto feesReceiptDto = feesCollectionService.collectFees(fees);
        return ResponseEntity.status(HttpStatus.OK).body(feesReceiptDto);
    }

    @GetMapping("/receipts")
    @Operation(summary = "Get all fees receipts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<List<FeesReceiptDto>> getAllFeesReceipts() {
        List<FeesReceiptDto> feesReceiptDtos = feesCollectionService.getAllFeesReceipts();
        return ResponseEntity.status(HttpStatus.OK).body(feesReceiptDtos);
    }
}