package com.command.itdaserver.domain.auth.domain.repository;

import com.command.itdaserver.domain.auth.domain.RememberMeToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RememberMeRepository extends CrudRepository<RememberMeToken, String> {
    Optional<RememberMeToken> findByUserId(String userId);
}
