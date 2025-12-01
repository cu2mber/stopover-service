package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.dto.response.StopoverResponse;

import java.util.List;
import java.util.Optional;

public interface CustomStopoverRepository {

    boolean existsByLocalAndStopover(int localNo, String stopoverName);

    List<StopoverResponse> findStopoverList(int localNo);

    Optional<Integer> findMaxSequenceByLocalNo(int localNo);

}
