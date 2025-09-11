package com.cu2mber.stopoverservice.service.impl;

import com.cu2mber.stopoverservice.common.exception.StopoverErrorCode;
import com.cu2mber.stopoverservice.common.exception.StopoverException;
import com.cu2mber.stopoverservice.dto.StopoverRequest;
import com.cu2mber.stopoverservice.dto.StopoverResponse;
import com.cu2mber.stopoverservice.dto.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.StopoverUpdateRequest;
import com.cu2mber.stopoverservice.repository.StopoverRepository;
import com.cu2mber.stopoverservice.service.StopoverService;
import com.cu2mber.stopoverservice.domain.Stopover;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StopoverServiceImpl implements StopoverService {
    private final StopoverRepository stopoverRepository;

    @Override
    public StopoverResponse create(StopoverRequest request) {

        if(stopoverRepository.existsByLocalAndStopover(request.getLocalNo(), request.getStopoverName())) {
            throw new StopoverException(StopoverErrorCode.STOPOVER_CONFLICT);
        }

        Stopover stopover = Stopover.ofNewStopover(request.getLocalNo(), request.getStopoverName(), request.getStopoverOrder());
        stopoverRepository.save(stopover);

        return getStopoverResponse(stopover);
    }

    @Override
    @Transactional(readOnly = true)
    public StopoverResponse getStopover(Long stopoverNo) {
        Stopover stopover = stopoverRepository.findById(stopoverNo)
                                                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, stopoverNo));

        return getStopoverResponse(stopover);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StopoverResponse> getStopoverList(int localNo) {
        return stopoverRepository.findStopoverList(localNo);
    }

    @Override
    public StopoverResponse update(StopoverUpdateRequest request) {
        Stopover stopover = stopoverRepository.findById(request.getStopoverNo())
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, request.getStopoverNo()));

        stopover.update(request.getStopoverName());
        return getStopoverResponse(stopover);
    }

    @Override
    public StopoverResponse updateOrder(StopoverUpdateOrderRequest request) {
        Stopover stopover = stopoverRepository.findById(request.getStopoverNo())
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, request.getStopoverNo()));

        stopover.updateOrder(request.getStopoverOrder());
        return getStopoverResponse(stopover);
    }

    @Override
    public void delete(Long stopoverNo) {
        Stopover stopover = stopoverRepository.findById(stopoverNo)
                                                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, stopoverNo));
        stopover.delete();
    }

    @Override
    public void deleteAll(int localNo) {
        List<StopoverResponse> stopovers = stopoverRepository.findStopoverList(localNo);

        if(!stopovers.isEmpty()) {
            stopoverRepository.deleteAllByLocalNo(localNo);
        }

    }

    private StopoverResponse getStopoverResponse(Stopover stopover) {
        return new StopoverResponse(stopover.getStopoverNo(), stopover.getLocalNo(), stopover.getStopoverName(), stopover.getStopoverOrder());
    }
}
