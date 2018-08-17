package com.fully.rest.api.repository;

import com.fully.rest.api.domain.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findBySocialId(Long id);

  Optional<Post> findBySocialIdAndId(Long socialId, Long id);
}
