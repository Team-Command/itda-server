package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.ApplyForm;
import com.command.itdaserver.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyFormRepository extends JpaRepository<ApplyForm, Long> {
    Optional<ApplyForm> findByPost(Post post);
    boolean existsByPost(Post post);
}
