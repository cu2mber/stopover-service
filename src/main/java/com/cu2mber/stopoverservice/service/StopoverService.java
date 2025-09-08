package com.cu2mber.stopoverservice.service;

import com.cu2mber.stopoverservice.dto.StopoverRequest;
import com.cu2mber.stopoverservice.dto.StopoverResponse;

import java.util.List;

public interface StopoverService {

    StopoverResponse create(StopoverRequest request);

    StopoverResponse getStopover(Long stopoverNo);

    List<StopoverResponse> getStopoverList(int localNo);

    void delete(Long stopoverNo);

    void deleteAll(int localNo);
}
