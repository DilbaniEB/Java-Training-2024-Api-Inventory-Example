package com.globant.training.inventorysample.domain.entity;

import com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity  // I am a DB entity
@Table(name = "products") // Configs of the table
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String sku;

  @Column(nullable = false, length = 255)
  private String name;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private ProductCategoryEnum productCategory;

  @Column(nullable = false, length = 512)
  private String description;

  @Column(nullable = false)
  private Double unitPrice;

  @Column(nullable = false, length = 100)
  private String color;

  @Column(nullable = false)
  private Boolean available;
}
