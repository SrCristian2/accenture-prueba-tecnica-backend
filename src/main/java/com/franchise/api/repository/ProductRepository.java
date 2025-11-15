package com.franchise.api.repository;

import com.franchise.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByBranchId(Long branchId);
    
   @Query("""
        SELECT p
        FROM Product p
        WHERE p.branch.franchise.id = :franchiseId
        AND p.stock = (
            SELECT MAX(p2.stock)
            FROM Product p2
            WHERE p2.branch.id = p.branch.id
        )
    """)
    List<Product> findProductsWithMaxStockPerBranch(@Param("franchiseId") Long franchiseId);
}
