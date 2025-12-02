package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.dto.response.StopoverResponse;

import java.util.List;
import java.util.Optional;

public interface CustomStopoverRepository {

    boolean existsByLocalAndStopover(Long memberLocalNo, String stopoverName);

    List<StopoverResponse> findStopoverList(Long memberLocalNo);

    Optional<Integer> findMaxSequenceByLocalNo(Long memberLocalNo);

}
