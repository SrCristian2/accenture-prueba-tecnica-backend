package com.franchise.api.controller;

import com.franchise.api.dto.ApiResponse;
import com.franchise.api.dto.ProductRequestDTO;
import com.franchise.api.dto.UpdateNameDTO;
import com.franchise.api.dto.UpdateStockDTO;
import com.franchise.api.model.Product;
import com.franchise.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    
    private final ProductService productService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(ApiResponse.success("Product created successfully", product), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", products));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success("Product retrieved successfully", product));
    }
    
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByBranch(@PathVariable Long branchId) {
        List<Product> products = productService.getProductsByBranch(branchId);
        return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", products));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully", null));
    }
    
    @PutMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<Product>> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStockDTO request) {
        Product product = productService.updateStock(id, request.getStock());
        return ResponseEntity.ok(ApiResponse.success("Product stock updated successfully", product));
    }
    
    @PutMapping("/{id}/name")
    public ResponseEntity<ApiResponse<Product>> updateProductName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNameDTO request) {
        Product product = productService.updateProductName(id, request.getName());
        return ResponseEntity.ok(ApiResponse.success("Product name updated successfully", product));
    }
}
