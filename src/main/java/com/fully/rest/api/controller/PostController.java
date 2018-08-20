package com.fully.rest.api.controller;

import com.fully.rest.api.domain.entity.Post;
import com.fully.rest.api.service.PostService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//TODO HATEOAS, Swagger 2.0, Client
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/socials/{socialId}/posts")
public class PostController {

  private PostService postService;

  @GetMapping
  public List<Post> findAll(@PathVariable Long socialId) {
    return postService.findBySocialId(socialId);
  }

  @GetMapping("/{id}")
  public Post findById(@PathVariable Long socialId, @PathVariable Long id) {
    return postService.findById(socialId, id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Post save(@Valid @RequestBody Post post, @PathVariable Long socialId) {
    return postService.save(post, socialId);
  }

  @PutMapping("/{id}")
  public Post update(@Valid @RequestBody Post post, @PathVariable Long socialId,
      @PathVariable Long id) {
    return postService.update(post, socialId, id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long socialId, @PathVariable Long id) {
    return postService.delete(socialId, id);
  }
}
