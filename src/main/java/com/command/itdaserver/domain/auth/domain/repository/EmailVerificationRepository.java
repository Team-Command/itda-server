package com.command.itdaserver.domain.auth.domain.repository;

import com.command.itdaserver.domain.auth.domain.EmailVerification;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {
}
