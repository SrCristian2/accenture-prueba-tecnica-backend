package com.franchise.api.service;

import com.franchise.api.dto.FranchiseRequestDTO;
import com.franchise.api.dto.ProductMaxStockDTO;
import com.franchise.api.exception.ResourceNotFoundException;
import com.franchise.api.model.Franchise;
import com.franchise.api.model.Product;
import com.franchise.api.repository.FranchiseRepository;
import com.franchise.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FranchiseService {
    
    private final FranchiseRepository franchiseRepository;
    private final ProductRepository productRepository;
    
    @Transactional
    public Franchise createFranchise(FranchiseRequestDTO request) {
        Franchise franchise = new Franchise();
        franchise.setName(request.getName());
        return franchiseRepository.save(franchise);
    }
    
    @Transactional(readOnly = true)
    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Franchise getFranchiseById(Long id) {
        return franchiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Franchise not found with ID: " + id));
    }
    
    @Transactional
    public Franchise updateFranchiseName(Long id, String newName) {
        Franchise franchise = getFranchiseById(id);
        franchise.setName(newName);
        return franchiseRepository.save(franchise);
    }
    
    @Transactional(readOnly = true)
    public List<ProductMaxStockDTO> getProductsWithMaxStockPerBranch(Long franchiseId) {
        getFranchiseById(franchiseId);
        
        List<Product> products = productRepository.findProductsWithMaxStockPerBranch(franchiseId);
        
        return products.stream()
                .map(p -> new ProductMaxStockDTO(
                        p.getId(),
                        p.getName(),
                        p.getStock(),
                        p.getBranch().getId(),
                        p.getBranch().getName()
                ))
                .collect(Collectors.toList());
    }
}
