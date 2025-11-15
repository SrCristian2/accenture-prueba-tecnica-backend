package com.franchise.api.service;

import com.franchise.api.dto.BranchRequestDTO;
import com.franchise.api.exception.ResourceNotFoundException;
import com.franchise.api.model.Franchise;
import com.franchise.api.model.Branch;
import com.franchise.api.repository.FranchiseRepository;
import com.franchise.api.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    
    @Transactional
    public Branch createBranch(BranchRequestDTO request) {
        Franchise franchise = franchiseRepository.findById(request.getFranchiseId())
                .orElseThrow(() -> new ResourceNotFoundException("Franchise not found with ID: " + request.getFranchiseId()));
        
        Branch branch = new Branch();
        branch.setName(request.getName());
        branch.setFranchise(franchise);
        
        return branchRepository.save(branch);
    }
    
    @Transactional(readOnly = true)
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<Branch> getBranchesByFranchise(Long franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId);
    }
    
    @Transactional
    public Branch updateBranchName(Long id, String newName) {
        Branch branch = getBranchById(id);
        branch.setName(newName);
        return branchRepository.save(branch);
    }
}
