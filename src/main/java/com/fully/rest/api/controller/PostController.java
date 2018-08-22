package com.fully.rest.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.fully.rest.api.domain.entity.Post;
import com.fully.rest.api.service.PostService;
import com.fully.rest.api.service.resource.PostResource;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO Pageable Swagger 2.0, Client
@AllArgsConstructor
@RestController
public class PostController {

  private PostService postService;

  @GetMapping(value = "/socials/{socialId}/posts", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<Resources<PostResource>> findAll(@PathVariable Long socialId) {
    Resources<PostResource> postResources = new Resources<>(
        postService.findBySocialId(socialId).stream()
            .map(e -> new PostResource(e, socialId))
            .collect(
                Collectors.toList()));
    postResources.add(linkTo(methodOn(PostController.class).findAll(socialId)).withSelfRel());
    postResources
        .add(linkTo(methodOn(SocialController.class).findById(socialId)).withRel("currentSocial"));
    return ResponseEntity.ok(postResources);
  }

  @GetMapping("/socials/{socialId}/posts/{id}")
  public ResponseEntity<PostResource> findById(@PathVariable Long socialId, @PathVariable Long id) {
    return ResponseEntity.ok(new PostResource(postService.findById(socialId, id), socialId));
  }

  @PostMapping("/socials/{socialId}/posts")
  public ResponseEntity<PostResource> save(@Valid @RequestBody Post post,
      @PathVariable Long socialId) {
    Post savedPost = postService.save(post, socialId);
    return ResponseEntity.created(
        linkTo(methodOn(PostController.class).findById(socialId, savedPost.getId())).toUri())
        .body(new PostResource(savedPost, socialId));
  }

  @PutMapping("/socials/{socialId}/posts/{id}")
  public ResponseEntity<PostResource> update(@Valid @RequestBody Post post,
      @PathVariable Long socialId,
      @PathVariable Long id) {
    return ResponseEntity.ok(new PostResource(postService.update(post, socialId, id), socialId));
  }

  @DeleteMapping("/socials/{socialId}/posts/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long socialId, @PathVariable Long id) {
    postService.delete(socialId, id);
    return ResponseEntity.noContent().build();
  }
}
