package com.fully.rest.api.controller;

import com.fully.rest.api.domain.entity.Social;
import com.fully.rest.api.service.SocialService;
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

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/socials")
public class SocialController {

  private SocialService socialService;

  @GetMapping
  public List<Social> findAll() {
    return socialService.findAll();
  }

  @GetMapping("/{id}")
  public Social findById(@PathVariable Long id) {
    return socialService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Social save(@Valid @RequestBody Social social) {
    return socialService.save(social);
  }

  @PutMapping("/{id}")
  public Social update(@Valid @RequestBody Social social, @PathVariable Long id) {
    return socialService.update(social, id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return socialService.delete(id);
  }
}
