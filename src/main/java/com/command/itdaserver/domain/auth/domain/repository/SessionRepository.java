package com.command.itdaserver.domain.auth.domain.repository;

import com.command.itdaserver.domain.auth.domain.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<UserSession, String> {

    void deleteByUserId(String userId);
}
