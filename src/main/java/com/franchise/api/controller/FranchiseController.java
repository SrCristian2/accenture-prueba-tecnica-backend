package com.franchise.api.controller;

import com.franchise.api.dto.ApiResponse;
import com.franchise.api.dto.FranchiseRequestDTO;
import com.franchise.api.dto.ProductMaxStockDTO;
import com.franchise.api.dto.UpdateNameDTO;
import com.franchise.api.model.Franchise;
import com.franchise.api.service.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FranchiseController {
    
    private final FranchiseService franchiseService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Franchise>> createFranchise(@Valid @RequestBody FranchiseRequestDTO request) {
        Franchise franchise = franchiseService.createFranchise(request);
        return new ResponseEntity<>(ApiResponse.success("Franchise created successfully", franchise), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Franchise>>> getAllFranchises() {
        List<Franchise> franchises = franchiseService.getAllFranchises();
        return ResponseEntity.ok(ApiResponse.success("Franchises retrieved successfully", franchises));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Franchise>> getFranchiseById(@PathVariable Long id) {
        Franchise franchise = franchiseService.getFranchiseById(id);
        return ResponseEntity.ok(ApiResponse.success("Franchise retrieved successfully", franchise));
    }
    
    @PutMapping("/{id}/name")
    public ResponseEntity<ApiResponse<Franchise>> updateFranchiseName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNameDTO request) {
        Franchise franchise = franchiseService.updateFranchiseName(id, request.getName());
        return ResponseEntity.ok(ApiResponse.success("Franchise name updated successfully", franchise));
    }
    
    @GetMapping("/{id}/products-max-stock")
    public ResponseEntity<ApiResponse<List<ProductMaxStockDTO>>> getProductsWithMaxStockPerBranch(@PathVariable Long id) {
        List<ProductMaxStockDTO> products = franchiseService.getProductsWithMaxStockPerBranch(id);
        return ResponseEntity.ok(ApiResponse.success("Products with max stock retrieved successfully", products));
    }
}
