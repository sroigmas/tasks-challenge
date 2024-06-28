package com.github.sroigmas.taskschallenge.infrastructure.rest;

import com.github.sroigmas.taskschallenge.infrastructure.exception.InfrastructureValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CommonRestExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(CommonRestExceptionHandler.class);

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentTypeMismatchExceptions(
      MethodArgumentTypeMismatchException e) {
    String message = e.getName() + " should be of type " + e.getRequiredType().getName();
    logger.error(message);
    return new ErrorResponse(HttpStatus.BAD_REQUEST, message);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException e) {
    logger.error(e.getMessage());
    return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException e) {
    logger.error(e.getMessage());
    return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(InfrastructureValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInfrastructureValidationExceptions(
      InfrastructureValidationException e) {
    logger.error(e.getMessage());
    return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleUnexpectedExceptions(Exception e) {
    logger.error(e.getMessage(), e);
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
  }
}
