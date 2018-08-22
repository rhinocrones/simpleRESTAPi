package com.fully.rest.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.fully.rest.api.controller.exception.RootController;
import com.fully.rest.api.domain.entity.Social;
import com.fully.rest.api.service.SocialService;
import com.fully.rest.api.service.resource.SocialResource;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/socials", produces = MediaTypes.HAL_JSON_VALUE)
public class SocialController {

  private SocialService socialService;

  @GetMapping
  public ResponseEntity<Resources<SocialResource>> findAll() {
    Resources<SocialResource> socialResources = new Resources<>(
        socialService.findAll().stream().map(SocialResource::new)
            .collect(
                Collectors.toList()));
    socialResources.add(linkTo(methodOn(SocialController.class).findAll()).withSelfRel());
    socialResources.add(linkTo(methodOn(RootController.class).root()).withSelfRel());
    return ResponseEntity.ok(socialResources);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SocialResource> findById(@PathVariable Long id) {
    return ResponseEntity.ok(new SocialResource(socialService.findById(id)));
  }

  @PostMapping
  public ResponseEntity<SocialResource> save(@Valid @RequestBody Social social) {
    Social savedSocial = socialService.save(social);
    return ResponseEntity.created(MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
        .buildAndExpand(savedSocial.getId()).toUri()).body(new SocialResource(savedSocial));
  }

  @PutMapping("/{id}")
  public ResponseEntity<SocialResource> update(@Valid @RequestBody Social social,
      @PathVariable Long id) {
    return ResponseEntity.ok(new SocialResource(socialService.update(social, id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return ResponseEntity.noContent().build();
  }
}
