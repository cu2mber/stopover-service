package com.cu2mber.stopoverservice.service;

import com.cu2mber.stopoverservice.dto.PageResult;
import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateOrderCommand;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverSummaryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StopoverService {

    StopoverResponse create(StopoverCreateCommand command);

    StopoverResponse getStopover(Long stopoverNo);

    PageResult<StopoverSummaryResponse> getStopoverPage(Pageable pageable);

    List<StopoverResponse> getStopoverList(Long memberLocalNo);

    StopoverResponse update(StopoverUpdateCommand command);

    void updateOrder(StopoverUpdateOrderCommand command);

    void delete(Long stopoverNo);

    void deleteAll(Long memberLocalNo);
}
