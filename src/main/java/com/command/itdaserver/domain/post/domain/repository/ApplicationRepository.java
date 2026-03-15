package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Application;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByPostAndApplicant(Post post, User applicant);

    List<Application> findAllByPost(Post post);

    Optional<Application> findByIdAndPost(Long id, Post post);

    void deleteAllByPost(Post post);

}
