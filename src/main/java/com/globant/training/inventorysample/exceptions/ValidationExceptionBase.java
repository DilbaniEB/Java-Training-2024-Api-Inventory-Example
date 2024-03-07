package com.globant.training.inventorysample.exceptions;
/**
 * Generic validation error in Dto.
 */
public class ValidationExceptionBase extends BaseInventoryApiException {
  public ValidationExceptionBase(String message) {
    super(message);
  }
}
