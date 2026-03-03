package com.command.itdaserver.domain.auth.domain.repository;

import com.command.itdaserver.domain.auth.domain.UserSession;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<UserSession, String> {
    Optional<UserSession> findByUserId(String userId);

    Optional<UserSession> findByUserPk(Long userPk);

    void deleteByUserId(String userId);
}
