package com.cu2mber.stopoverservice.service;

import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateCommand;
import com.cu2mber.stopoverservice.dto.request.StopoverCreateRequest;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateRequest;

import java.util.List;

public interface StopoverService {

    StopoverResponse create(StopoverCreateCommand command);

    StopoverResponse getStopover(Long stopoverNo);

    List<StopoverResponse> getStopoverList(Long memberLocalNo);

    StopoverResponse update(StopoverUpdateCommand command);

    StopoverResponse updateOrder(Long stopoverNo, StopoverUpdateOrderRequest request);

    void delete(Long stopoverNo);

    void deleteAll(Long memberLocalNo);
}
