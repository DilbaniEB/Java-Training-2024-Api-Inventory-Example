package com.globant.training.inventorysample.repository;
import com.globant.training.inventorysample.domain.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // automagic method to find first by SKU
  Optional<Product> findBySku(String sku);

  // Automagic method to test if exist by SKU
  boolean existsBySku(String sku);
}
