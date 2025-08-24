package com.oeso.stopoverservice.repository;

import com.oeso.stopoverservice.domain.Stopover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopoverRepository extends JpaRepository<Stopover, Long> {
}
