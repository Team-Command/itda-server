package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
