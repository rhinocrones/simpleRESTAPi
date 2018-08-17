package com.fully.rest.api.service.impl;

import com.fully.rest.api.controller.exception.SocialIdMismatchException;
import com.fully.rest.api.controller.exception.SocialNotFoundException;
import com.fully.rest.api.domain.entity.Social;
import com.fully.rest.api.repository.SocialRepository;
import com.fully.rest.api.service.SocialService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SocialServiceImpl implements SocialService {

  private SocialRepository socialRepository;

  @Override
  public List<Social> findAll() {
    return socialRepository.findAll();
  }

  @Override
  public Social findById(Long id) {
    return socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);
  }

  @Override
  public Social save(Social social) {
    return socialRepository.save(social);
  }

  @Override
  public Social update(Social social, Long id) {
    if (social.getId() == null || !social.getId().equals(id)) {
      throw new SocialIdMismatchException();
    }
    return socialRepository.findById(id).map(e -> processUpdate(e, social))
        .orElseThrow(SocialNotFoundException::new);
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    return socialRepository.findById(id).map(e -> processDelete(id))
        .orElseThrow(SocialIdMismatchException::new);
  }

  private Social processUpdate(Social oldOne, Social newOne) {
    oldOne.setName(newOne.getName());
    oldOne.setDescription(newOne.getDescription());
    return socialRepository.save(oldOne);
  }

  private ResponseEntity<Void> processDelete(Long id) {
    socialRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
