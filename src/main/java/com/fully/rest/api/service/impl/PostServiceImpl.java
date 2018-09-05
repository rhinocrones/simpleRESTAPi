package com.fully.rest.api.service.impl;

import com.fully.rest.api.controller.exception.PostIdMismatchException;
import com.fully.rest.api.controller.exception.PostNotFoundException;
import com.fully.rest.api.controller.exception.SocialNotFoundException;
import com.fully.rest.api.domain.entity.Post;
import com.fully.rest.api.domain.entity.Social;
import com.fully.rest.api.repository.PostRepository;
import com.fully.rest.api.repository.SocialRepository;
import com.fully.rest.api.service.PostService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  private SocialRepository socialRepository;


  @Override
  @Cacheable(cacheNames = "allForOneSocial", key = "#socialId")
  public List<Post> findBySocialId(Long socialId) {
    List<Post> posts = postRepository.findBySocialId(socialId);
    if (posts.isEmpty()) {
      throw new SocialNotFoundException();
    }
    return posts;
  }

  @Override
  @Cacheable(value = "posts", key = "{ #socialId, #id}")
  public Post findById(Long socialId, Long id) {
    if (!socialRepository.existsById(socialId)) {
      throw new SocialNotFoundException();
    }
    return postRepository.findBySocialIdAndId(socialId, id).orElseThrow(PostNotFoundException::new);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(cacheNames = "allForOneSocial", key = "#socialId", beforeInvocation = true),
      @CacheEvict(cacheNames = "posts", allEntries = true, beforeInvocation = true)})
  public Post save(Post post, Long socialId) {
    return socialRepository.findById(socialId).map(e -> processSave(e, post))
        .orElseThrow(SocialNotFoundException::new);
  }

  @Override
  @Caching(evict = @CacheEvict(cacheNames = "allForOneSocial", key = "#socialId", beforeInvocation = true),
      put = @CachePut(cacheNames = "posts", key = "{ #socialId, #id}"))
  public Post update(Post post, Long socialId, Long id) {
    if (post.getId() == null || !post.getId().equals(id)) {
      throw new PostIdMismatchException();
    }
    if (!socialRepository.existsById(socialId)) {
      throw new SocialNotFoundException();
    }
    return postRepository.findById(id).map(e -> processUpdate(e, post))
        .orElseThrow(PostNotFoundException::new);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(cacheNames = "allForOneSocial", key = "#socialId", beforeInvocation = true),
      @CacheEvict(cacheNames = "posts", key = "{ #socialId, #id}", beforeInvocation = true)})
  public void delete(Long socialId, Long id) {
    if (!socialRepository.existsById(socialId)) {
      throw new SocialNotFoundException();
    }
    postRepository.findById(id).map(e -> processDelete(id))
        .orElseThrow(PostNotFoundException::new);
  }

  private Post processSave(Social social, Post post) {
    post.setSocial(social);
    return postRepository.save(post);
  }

  private Post processUpdate(Post oldPost, Post newPost) {
    oldPost.setMessage(newPost.getMessage());
    return postRepository.save(oldPost);
  }

  private ResponseEntity<Void> processDelete(Long id) {
    postRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
