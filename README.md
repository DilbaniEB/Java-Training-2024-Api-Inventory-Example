# Java-Training-2024-Api-Inventory-Example
Example of basic Restful API with Springboot

# Configuration
Application is set to listen by default on port 9090. It could be changed by editing property `server.port`

It works out of the box by creating an in-memory H2 database. Some seeding data is provided.

File `insomnia_project/Insomnia_api_sample.json` contains an Insomnia project with exaple endpoints.

# API Contract
TODO: Generate Swagger documentation.

Idea of API: Inventory of product for a Clothes and apparel store.

## Product DTO
Attributes:
- `sku`: `String` with product inventory SKU Code. It must be unique.
- `name`: `String` with product name.
- `description`: `String` with longer product description.
- `category`: Enumeration with product category. Valid values from Java `enum` `com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum`.
- `color`: Color of the product. Valid values from Java `enum` `com.globant.training.inventorysample.domain.enumerations.ColorEnum`.
- `unitPrice`: `Double` value with price of product in USD.
- `availbale`: `Boolean` flag for availability of product to sale.

````
{
  "sku": "P-0009",
  "name": "White Unisex T-Shirt",
  "category": "SHIRTS_AND_T_SHIRTS",
  "description": "Unisex T-Shirt Color white short sleeve Size M,
  "unitPrice": 35.99,
  "color": "WHITE",
  "available": true
}
````
## GET /products
Return a list of all products.

## GET /products/{sku}
Return the product with SKU in parameters. Returns `404` if product does not exist in catalog.

## POST /products
Create new product. ProductDto data of object to be created must be in request body. 
Returns `400` if product have some missing fields, and returns `409` if product with SKU already exists 
in catalog.

# Example classes

## Package Scan configuration
Main application class `com.globant.training.inventorysample.Application`.

## Initial data seet application
Class that implements interface CommandLineRunner `com.globant.training.inventorysample.runner.ProductSeeder` 
has an example of code for initial database population with test data.

## Validator example
`com.globant.training.inventorysample.validator.ProductDtoValidator`

## Dto Mappers
In package `com.globant.training.inventorysample.mapper` there are a couple of examples of mappers using 
Spring's `Converter<S, T>`interface.

## Controler Advice with exception handlers
Class `com.globant.training.inventorysample.controller.ExceptionHandlerAdvicer.java` has an example of controllerAdvice 
annotated class with global Exception Handler. You should look at exception hierarchy in package 
`com.globant.training.inventorysample.exceptions`

