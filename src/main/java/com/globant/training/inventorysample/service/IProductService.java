package com.globant.training.inventorysample.service;
import com.globant.training.inventorysample.domain.dto.ProductDto;
import java.util.List;

/**
 * Contract for service of Product entity
 */
public interface IProductService {
  /**
   * Finds all products in DB
   * @return a List of all products in DB
   */
  List<ProductDto> findAllProducts();

  /**
   * Insert a new Product record in DB.
   * @param productWriteDto DTO with the product data to be inserted
   * @return DTO with data or the product inserted or throw  exception
   * if product with SKU already exists
   */
  ProductDto createProduct(ProductDto productWriteDto);

  /**
   * Finds product in DB for given SKU code.
   *
   * @param sku SKU to be found
   * @return ProductDto instance with product data or exception it
   * product not found.
   */
  ProductDto findProductBySku(String sku);

}
