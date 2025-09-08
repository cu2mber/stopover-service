package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.domain.Stopover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopoverRepository extends JpaRepository<Stopover, Long>, CustomStopoverRepository {
}
