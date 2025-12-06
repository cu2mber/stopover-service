package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.domain.Stopover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StopoverRepository extends JpaRepository<Stopover, Long>, CustomStopoverRepository {

    Optional<Stopover> findByMemberLocalNo(Long memberLocalNo);

    List<Stopover> findAllByMemberLocalNo(Long memberLocalNo);
}
