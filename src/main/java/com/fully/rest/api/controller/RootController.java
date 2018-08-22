package com.fully.rest.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

  @GetMapping(value = "/", produces = MediaTypes.HAL_JSON_VALUE)
  public ResourceSupport root() {
    ResourceSupport rootResource = new ResourceSupport();
    rootResource.add(
        linkTo(methodOn(RootController.class).root()).withSelfRel(),
        linkTo(methodOn(SocialController.class).findAll()).withRel("socials"));
    return rootResource;
  }
}
