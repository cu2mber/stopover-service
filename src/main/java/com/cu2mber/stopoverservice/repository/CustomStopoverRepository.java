package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomStopoverRepository {

    boolean existsByLocalAndStopover(Long memberLocalNo, String stopoverName);

    Page<StopoverSummaryResponse> findStopoverPage(Pageable pageable);

    List<StopoverResponse> findStopoverList(Long memberLocalNo);

    Optional<Integer> findMaxSequenceByLocalNo(Long memberLocalNo);

}
