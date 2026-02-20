package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
