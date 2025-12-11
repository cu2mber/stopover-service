package com.cu2mber.stopoverservice.service.impl;

import com.cu2mber.stopoverservice.common.exception.StopoverErrorCode;
import com.cu2mber.stopoverservice.common.exception.StopoverException;
import com.cu2mber.stopoverservice.dto.PageResult;
import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateOrderCommand;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverSummaryResponse;
import com.cu2mber.stopoverservice.repository.StopoverRepository;
import com.cu2mber.stopoverservice.service.StopoverService;
import com.cu2mber.stopoverservice.domain.Stopover;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PageResult<StopoverSummaryResponse> getStopoverPage(Pageable pageable) {
        Page<StopoverSummaryResponse> stopoverPage = stopoverRepository.findStopoverPage(pageable);
        /**
         * todo 지자체 이름 설정 수정
         * 방법 1. Cache + BatchAPI
         */
        stopoverPage.forEach(stopover -> {
            if(stopover.getMemberLocalNo() == 1) {
                stopover.setLocalName("김해시");
            } else if(stopover.getMemberLocalNo() == 2) {
                stopover.setLocalName("부산 남구");
            }
        });
        List<StopoverSummaryResponse> summaryList = stopoverPage.stream().toList();

        return new PageResult<>(summaryList, stopoverPage.getTotalElements(), stopoverPage.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StopoverResponse> getStopoverList(Long memberLocalNo) {
        List<StopoverResponse> stopoverList = stopoverRepository.findStopoverList(memberLocalNo);
        stopoverList.forEach(stopover -> {
            if(stopover.getMemberLocalNo() == 1) {
                stopover.setLocalName("김해시");
            } else if(stopover.getMemberLocalNo() == 2) {
                stopover.setLocalName("부산 남구");
            }
        });
        return stopoverList;
    }

    @Override
    public StopoverResponse update(StopoverUpdateCommand command) {
        Stopover stopover = stopoverRepository.findById(command.stopoverNo())
                .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND, command.stopoverName()));

        existStopover(command.memberLocalNo(), command.stopoverName());
        stopover.update(command.stopoverName());
        stopover.normalizeName();
        return getStopoverResponse(stopover);
    }

    @Override
    public void updateOrder(StopoverUpdateOrderCommand command) {

        for(StopoverUpdateOrderCommand.UpdateOrderInfo req : command.info()) {
            Stopover stopover = stopoverRepository.findById(req.stopoverNo())
                    .orElseThrow(() -> new StopoverException(StopoverErrorCode.STOPOVER_NOT_FOUND));

            stopover.updateOrder(req.stopoverSequence());
        }
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
        return new StopoverResponse(stopover.getStopoverNo(), stopover.getMemberLocalNo(), stopover.getStopoverName(), stopover.getStopoverSequence(), stopover.getCreatedAt());
    }

    private void existStopover(Long memberLocalNo, String stopoverName) {
        if(stopoverRepository.existsByLocalAndStopover(memberLocalNo, stopoverName)) {
            throw new StopoverException(StopoverErrorCode.STOPOVER_CONFLICT);
        }
    }
}
