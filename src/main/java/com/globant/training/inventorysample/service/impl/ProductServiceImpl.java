package com.globant.training.inventorysample.service.impl;
import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.exceptions.ProductNotFoundExceptionBase;
import com.globant.training.inventorysample.exceptions.ProductSkuAlreadyExistExceptionBase;
import com.globant.training.inventorysample.mapper.ProductDtoToProductEntityConverter;
import com.globant.training.inventorysample.mapper.ProductEntityToProductDtoConverter;
import com.globant.training.inventorysample.repository.ProductRepository;
import com.globant.training.inventorysample.service.IProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Product service implementation.
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
  // logger instance
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

  private final ProductRepository productRepository;
  private final ProductEntityToProductDtoConverter productEntityToProductDtoConverter;
  private final ProductDtoToProductEntityConverter productDtoToProductEntityConverter;

  @Override
  public List<ProductDto> findAllProducts() {
    LOGGER.info("Begin method findAllProducts");
    return productRepository
        .findAll()
        .stream()
        .map(productEntityToProductDtoConverter::convert)
        .toList();
  }

  @Override
  public ProductDto findProductBySku(String sku) {
    LOGGER.info("Begin method findProductBySku sku={}", sku);
    return productRepository
        .findBySku(sku)
        .stream()
        .map(productEntityToProductDtoConverter::convert)
        .findFirst()
        .orElseThrow(() -> {
          LOGGER.info(
              "method findProductBySku with SKU={} throws ProductNotFoundException");
          return new ProductNotFoundExceptionBase(
              String.format("Product with SKU=%s not found", sku));
        });
  }

  @Override
  public ProductDto createProduct(ProductDto product) {
    LOGGER.info("Begin method createProduct dto={}", product);
    final String sku = product.getSku().trim();
    if (productRepository.existsBySku(sku)) {
      LOGGER.info(
          "method findProductBySku with SKU={} throws ProductSkuAlreadyExistException");
      throw new ProductSkuAlreadyExistExceptionBase(
          String.format("SKU=%s already exists in DB", sku));
    }
    productRepository.save(productDtoToProductEntityConverter.convert(product));
    return product;
  }
}
