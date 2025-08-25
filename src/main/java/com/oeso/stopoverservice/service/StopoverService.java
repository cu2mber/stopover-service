package com.oeso.stopoverservice.service;

import com.oeso.stopoverservice.dto.StopoverRequest;
import com.oeso.stopoverservice.dto.StopoverResponse;

import java.util.List;

public interface StopoverService {

    StopoverResponse create(StopoverRequest request);

    StopoverResponse getStopover(Long stopoverNo);

    List<StopoverResponse> getStopoverList(int localNo);

    void delete(Long stopoverNo);

    void deleteAll(int localNo);
}
