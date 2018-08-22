package com.fully.rest.api.service.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.fully.rest.api.controller.PostController;
import com.fully.rest.api.controller.SocialController;
import com.fully.rest.api.domain.entity.Social;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class SocialResource extends ResourceSupport {

  private Social social;

  public SocialResource(Social social) {
    this.social = social;
    add(linkTo(SocialController.class).withRel("socials"));
    add(linkTo(methodOn(PostController.class).findAll(social.getId())).withRel("posts"));
    add(linkTo(methodOn(SocialController.class).findById(social.getId())).withSelfRel());
  }
}
