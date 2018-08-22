package com.fully.rest.api.service.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fully.rest.api.controller.PostController;
import com.fully.rest.api.domain.entity.Post;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class PostResource extends ResourceSupport {

  private Post post;

  @JsonIgnore
  private Long socialId;

  public PostResource(Post post, Long socialId) {
    this.post = post;
    this.socialId = socialId;
    add(linkTo(methodOn(PostController.class).findAll(socialId)).withRel("posts"));
    add(linkTo(methodOn(PostController.class).findById(socialId, post.getId())).withSelfRel());
  }
}
