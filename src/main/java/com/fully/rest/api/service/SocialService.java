package com.fully.rest.api.service;

import com.fully.rest.api.domain.entity.Social;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface SocialService {

  List<Social> findAll();

  Social findById(Long id);

  Social save(Social social);

  Social update(Social social, Long id);

  ResponseEntity<Void> delete(Long id);
}
