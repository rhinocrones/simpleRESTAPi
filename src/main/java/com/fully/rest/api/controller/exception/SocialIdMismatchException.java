package com.fully.rest.api.controller.exception;

public class SocialIdMismatchException extends RuntimeException {

  public SocialIdMismatchException() {
    super();
  }

  public SocialIdMismatchException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public SocialIdMismatchException(final String message) {
    super(message);
  }

  public SocialIdMismatchException(final Throwable cause) {
    super(cause);
  }
}
