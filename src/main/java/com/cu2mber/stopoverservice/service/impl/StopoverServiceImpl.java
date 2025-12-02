package com.cu2mber.stopoverservice.service.impl;

import com.cu2mber.stopoverservice.common.exception.StopoverErrorCode;
import com.cu2mber.stopoverservice.common.exception.StopoverException;
import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateCommand;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateOrderRequest;
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

        existStopover(command.memberLocalNo(), command.stopoverName());
        int nextSequence = stopoverRepository.findMaxSequenceByLocalNo(command.memberLocalNo())
                .orElse(0) + 1;

        Stopover stopover = Stopover.ofNewStopover(command.memberLocalNo(), command.stopoverName(), nextSequence);
        stopover.normalizeName();
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
    public List<StopoverResponse> getStopoverList(Long memberLocalNo) {
        return stopoverRepository.findStopoverList(memberLocalNo);
    }

    @Override
    public StopoverResponse update(StopoverUpdateCommand command) {
        Stopover stopover = stopoverRepository.findById(command.stopoverNo())
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, command.stopoverName()));

        existStopover(command.memberLocalNo(), command.stopoverName());

        stopover.update(command.stopoverName());
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
    public void deleteAll(Long memberLocalNo) {
        List<Stopover> stopovers = stopoverRepository.findAllByMemberLocalNo(memberLocalNo);

        if(stopovers.isEmpty()) {
            throw new StopoverException(StopoverErrorCode.STOPOVER_LIST_EMPTY);
        }

        stopovers.forEach(Stopover::delete);

    }

    private StopoverResponse getStopoverResponse(Stopover stopover) {
        return new StopoverResponse(stopover.getStopoverNo(), stopover.getMemberLocalNo(), stopover.getStopoverName(), stopover.getStopoverSequence());
    }

    private void existStopover(Long memberLocalNo, String stopoverName) {
        if(stopoverRepository.existsByLocalAndStopover(memberLocalNo, stopoverName)) {
            throw new StopoverException(StopoverErrorCode.STOPOVER_CONFLICT);
        }
    }
}
