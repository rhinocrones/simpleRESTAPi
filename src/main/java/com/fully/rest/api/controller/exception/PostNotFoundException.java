package com.fully.rest.api.controller.exception;

public class PostNotFoundException extends RuntimeException {

  public PostNotFoundException() {
    super();
  }

  public PostNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PostNotFoundException(final String message) {
    super(message);
  }

  public PostNotFoundException(final Throwable cause) {
    super(cause);
  }
}
