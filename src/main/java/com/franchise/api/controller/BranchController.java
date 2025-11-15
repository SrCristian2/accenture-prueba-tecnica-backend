package com.franchise.api.controller;

import com.franchise.api.dto.ApiResponse;
import com.franchise.api.dto.BranchRequestDTO;
import com.franchise.api.dto.UpdateNameDTO;
import com.franchise.api.model.Branch;
import com.franchise.api.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BranchController {
    
    private final BranchService branchService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Branch>> createBranch(@Valid @RequestBody BranchRequestDTO request) {
        Branch branch = branchService.createBranch(request);
        return new ResponseEntity<>(ApiResponse.success("Branch created successfully", branch), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Branch>>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(ApiResponse.success("Branches retrieved successfully", branches));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Branch>> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        return ResponseEntity.ok(ApiResponse.success("Branch retrieved successfully", branch));
    }
    
    @GetMapping("/franchise/{franchiseId}")
    public ResponseEntity<ApiResponse<List<Branch>>> getBranchesByFranchise(@PathVariable Long franchiseId) {
        List<Branch> branches = branchService.getBranchesByFranchise(franchiseId);
        return ResponseEntity.ok(ApiResponse.success("Branches retrieved successfully", branches));
    }
    
    @PutMapping("/{id}/name")
    public ResponseEntity<ApiResponse<Branch>> updateBranchName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNameDTO request) {
        Branch branch = branchService.updateBranchName(id, request.getName());
        return ResponseEntity.ok(ApiResponse.success("Branch name updated successfully", branch));
    }
}
