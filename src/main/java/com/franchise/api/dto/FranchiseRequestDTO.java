package com.franchise.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseRequestDTO {
    
    @NotBlank(message = "Franchise name is required")
    private String name;
}
