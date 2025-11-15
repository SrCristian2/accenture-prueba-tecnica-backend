package com.franchise.api.service;

import com.franchise.api.dto.ProductRequestDTO;
import com.franchise.api.exception.ResourceNotFoundException;
import com.franchise.api.model.Product;
import com.franchise.api.model.Branch;
import com.franchise.api.repository.ProductRepository;
import com.franchise.api.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    
    @Transactional
    public Product createProduct(ProductRequestDTO request) {
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + request.getBranchId()));
        
        Product product = new Product();
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setBranch(branch);
        
        return productRepository.save(product);
    }
    
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<Product> getProductsByBranch(Long branchId) {
        return productRepository.findByBranchId(branchId);
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    
    @Transactional
    public Product updateStock(Long id, Integer newStock) {
        Product product = getProductById(id);
        product.setStock(newStock);
        return productRepository.save(product);
    }
    
    @Transactional
    public Product updateProductName(Long id, String newName) {
        Product product = getProductById(id);
        product.setName(newName);
        return productRepository.save(product);
    }
}
