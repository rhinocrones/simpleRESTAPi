package com.fully.rest.api.repository;

import com.fully.rest.api.domain.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social, Long> {

}
