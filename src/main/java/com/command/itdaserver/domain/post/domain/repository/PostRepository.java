package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"writer"})
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
