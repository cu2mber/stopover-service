package com.cu2mber.stopoverservice.service.impl;

import com.cu2mber.stopoverservice.common.exception.StopoverErrorCode;
import com.cu2mber.stopoverservice.common.exception.StopoverException;
import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateRequest;
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
    public StopoverResponse create(StopoverCreateCommand command) {

        existStopover(command.localNo(), command.stopoverName());
        int nextSequence = stopoverRepository.findMaxSequenceByLocalNo(command.localNo())
                .orElse(0) + 1;

        Stopover stopover = Stopover.ofNewStopover(command.localNo(), command.stopoverName(), nextSequence);
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
    public StopoverResponse update(Long stopoverNo, StopoverUpdateRequest request) {
        Stopover stopover = stopoverRepository.findById(stopoverNo)
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, request.getStopoverName()));

        existStopover(request.getLocalNo(), request.getStopoverName());

        stopover.update(request.getStopoverName());
        return getStopoverResponse(stopover);
    }

    @Override
    public StopoverResponse updateOrder(Long stopoverNo, StopoverUpdateOrderRequest request) {
        Stopover stopover = stopoverRepository.findById(stopoverNo)
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND));

        stopover.updateOrder(request.stopoverSequence());
        return getStopoverResponse(stopover);
    }

    @Override
    public void delete(Long stopoverNo) {
        Stopover stopover = stopoverRepository.findById(stopoverNo)
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND));
        stopover.delete();
    }

    @Override
    public void deleteAll(int localNo) {
        List<Stopover> stopovers = stopoverRepository.findAllByLocalNo(localNo);

        if(stopovers.isEmpty()) {
            throw new StopoverException(StopoverErrorCode.STOPOVER_LIST_EMPTY);
        }

        stopovers.forEach(Stopover::delete);

    }

    private StopoverResponse getStopoverResponse(Stopover stopover) {
        return new StopoverResponse(stopover.getStopoverNo(), stopover.getLocalNo(), stopover.getStopoverName(), stopover.getStopoverSequence());
    }

    private void existStopover(int localNo, String stopoverName) {
        if(stopoverRepository.existsByLocalAndStopover(localNo, stopoverName)) {
            throw new StopoverException(StopoverErrorCode.STOPOVER_CONFLICT);
        }
    }
}
