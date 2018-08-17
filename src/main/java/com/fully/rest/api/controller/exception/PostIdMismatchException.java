package com.fully.rest.api.controller.exception;

public class PostIdMismatchException extends RuntimeException {

  public PostIdMismatchException() {
    super();
  }

  public PostIdMismatchException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PostIdMismatchException(final String message) {
    super(message);
  }

  public PostIdMismatchException(final Throwable cause) {
    super(cause);
  }
}
