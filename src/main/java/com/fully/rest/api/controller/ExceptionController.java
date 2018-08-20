package com.fully.rest.api.controller;

import com.fully.rest.api.controller.exception.PostIdMismatchException;
import com.fully.rest.api.controller.exception.PostNotFoundException;
import com.fully.rest.api.controller.exception.SocialIdMismatchException;
import com.fully.rest.api.controller.exception.SocialNotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler({SocialNotFoundException.class})
  protected ResponseEntity<Object> handleSocialNotFound(
      Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, "Social not found",
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({PostNotFoundException.class})
  protected ResponseEntity<Object> handlePostNotFound(
      Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, "Post not found",
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({SocialIdMismatchException.class})
  public ResponseEntity<Object> handleSocialBadRequest(
      Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, "Wrong id for social network",
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({PostIdMismatchException.class})
  public ResponseEntity<Object> handlePostBadRequest(
      Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, "Wrong id for post",
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ConstraintViolationException.class,
      DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleBadRequest(
      Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, "You have some issue with request body",
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
