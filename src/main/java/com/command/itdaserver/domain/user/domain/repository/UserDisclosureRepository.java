package com.command.itdaserver.domain.user.domain.repository;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDisclosureRepository extends JpaRepository<UserDisclosure, Long> {
    Optional<UserDisclosure> findByUser(User user);
}
