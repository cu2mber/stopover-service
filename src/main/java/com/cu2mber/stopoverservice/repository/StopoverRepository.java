package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.domain.Stopover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StopoverRepository extends JpaRepository<Stopover, Long>, CustomStopoverRepository {

    List<Stopover> findAllByLocalNo(int localNo);
}
