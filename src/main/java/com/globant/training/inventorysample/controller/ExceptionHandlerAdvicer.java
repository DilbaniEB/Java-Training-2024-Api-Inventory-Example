package com.globant.training.inventorysample.controller;

import com.globant.training.inventorysample.domain.dto.ErrorDto;
import com.globant.training.inventorysample.exceptions.EntityAlreadyExistExceptionBase;
import com.globant.training.inventorysample.exceptions.EntityNotFoundExceptionBase;
import com.globant.training.inventorysample.exceptions.ValidationExceptionBase;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception advice use to handle all controllers' exceptions.
 */
@Component
@RestControllerAdvice
public class ExceptionHandlerAdvicer {
  /**
   * Exception handler for ValidaitonException errors
   * In this case response to be returned will be 400
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({ValidationExceptionBase.class})
  public ResponseEntity<ErrorDto> handlerValidationException(ValidationExceptionBase e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST.value())
        .body(ErrorDto
            .builder()
            .timeStamp(LocalDateTime.now().toString())
            .message("validation error: " + e.getMessage())
            .build());
  }

  /**
   * Exception handler for EntityNotFoundException errors
   * You must create an EntityNotFoundException child exception
   * for specific not found entity.
   *
   * In this case response to be returned will be 404
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({EntityNotFoundExceptionBase.class})
  public ResponseEntity<ErrorDto> handlerEntityNotFoundException(EntityNotFoundExceptionBase e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorDto
            .builder()
            .uuid(UUID.randomUUID().toString())
            .timeStamp(LocalDateTime.now().toString())
            .message("validation error: " + e.getMessage())
            .build());
  }

  /**
   * Exception handler for EntityAlreadyExistException errors
   * You must create an EntityAlreadyExistException child exception
   * for specific not found entity.
   *
   * In this case response to be returned will be 409
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({EntityAlreadyExistExceptionBase.class})
  public ResponseEntity<ErrorDto> handlerEntityAlreadyExistException(EntityAlreadyExistExceptionBase e) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ErrorDto
            .builder()
            .uuid(UUID.randomUUID().toString())
            .timeStamp(LocalDateTime.now().toString())
            .message("validation error: " + e.getMessage())
            .build());
  }

  /**
   * Exception handler for generic error, and response will be 500
   *
   * This method ALWAYS must be THE LAST ExceptionHandler in the class.
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorDto> handlerGeneric(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ErrorDto
            .builder()
            .uuid(UUID.randomUUID().toString())
            .timeStamp(LocalDateTime.now().toString())
            .message("generic error: " + e.getMessage())
            .build());
  }
}
