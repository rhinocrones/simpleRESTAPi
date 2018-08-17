package com.fully.rest.api.controller.exception;

public class SocialNotFoundException extends RuntimeException {

  public SocialNotFoundException() {
    super();
  }

  public SocialNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public SocialNotFoundException(final String message) {
    super(message);
  }

  public SocialNotFoundException(final Throwable cause) {
    super(cause);
  }
}