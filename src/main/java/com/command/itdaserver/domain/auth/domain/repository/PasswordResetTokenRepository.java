package com.command.itdaserver.domain.auth.domain.repository;

import com.command.itdaserver.domain.auth.domain.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,String> {
}
