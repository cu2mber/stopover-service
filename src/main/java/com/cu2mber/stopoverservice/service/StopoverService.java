package com.cu2mber.stopoverservice.service;

import com.cu2mber.stopoverservice.dto.StopoverRequest;
import com.cu2mber.stopoverservice.dto.StopoverResponse;
import com.cu2mber.stopoverservice.dto.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.StopoverUpdateRequest;

import java.util.List;

public interface StopoverService {

    StopoverResponse create(StopoverRequest request);

    StopoverResponse getStopover(Long stopoverNo);

    List<StopoverResponse> getStopoverList(int localNo);

    StopoverResponse update(StopoverUpdateRequest request);

    StopoverResponse updateOrder(StopoverUpdateOrderRequest request);

    void delete(Long stopoverNo);

    void deleteAll(int localNo);
}
