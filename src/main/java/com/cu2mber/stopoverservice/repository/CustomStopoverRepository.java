package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.dto.StopoverResponse;

import java.util.List;

public interface CustomStopoverRepository {

    boolean existsByLocalAndStopover(int localNo, String stopoverName);

    List<StopoverResponse> findStopoverList(int localNo);

}
