package com.franchise.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequestDTO {
    
    @NotBlank(message = "Branch name is required")
    private String name;
    
    @NotNull(message = "Franchise ID is required")
    private Long franchiseId;
}
