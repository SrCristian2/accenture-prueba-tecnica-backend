package com.franchise.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaxStockDTO {
    
    private Long productId;
    private String productName;
    private Integer stock;
    private Long branchId;
    private String branchName;
}
