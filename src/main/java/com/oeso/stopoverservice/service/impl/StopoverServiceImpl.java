package com.oeso.stopoverservice.service.impl;

import com.oeso.stopoverservice.common.exception.ConflictException;
import com.oeso.stopoverservice.common.exception.NotFoundException;
import com.oeso.stopoverservice.domain.Stopover;
import com.oeso.stopoverservice.dto.StopoverRequest;
import com.oeso.stopoverservice.dto.StopoverResponse;
import com.oeso.stopoverservice.repository.StopoverRepository;
import com.oeso.stopoverservice.service.StopoverService;
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
            throw new ConflictException();
        }

        Stopover stopover = Stopover.ofNewStopover(request.getLocalNo(), request.getStopoverName(), request.getStopoverOrder());
        stopoverRepository.save(stopover);

        return getStopoverResponse(stopover);
    }

    @Override
    @Transactional(readOnly = true)
    public StopoverResponse getStopover(Long stopoverNo) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StopoverResponse> getStopoverList(int localNo) {
        return List.of();
    }

    @Override
    public void delete(Long stopoverNo) {
        Stopover stopover = stopoverRepository.findById(stopoverNo).orElseThrow(NotFoundException::new);
        stopover.delete(stopoverNo);
    }

    @Override
    public void deleteAll(int localNo) {

    }

    private StopoverResponse getStopoverResponse(Stopover stopover) {
        return new StopoverResponse(stopover.getLocalNo(), stopover.getStopoverName(), stopover.getStopoverOrder());
    }
}
