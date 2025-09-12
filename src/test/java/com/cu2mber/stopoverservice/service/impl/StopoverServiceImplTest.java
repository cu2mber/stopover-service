package com.cu2mber.stopoverservice.service.impl;

import com.cu2mber.stopoverservice.common.exception.StopoverException;
import com.cu2mber.stopoverservice.domain.Stopover;
import com.cu2mber.stopoverservice.dto.StopoverRequest;
import com.cu2mber.stopoverservice.dto.StopoverResponse;
import com.cu2mber.stopoverservice.dto.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.StopoverUpdateRequest;
import com.cu2mber.stopoverservice.repository.StopoverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StopoverServiceImplTest {

    @Mock
    StopoverRepository stopoverRepository;

    @InjectMocks
    StopoverServiceImpl stopoverService;


    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("경유지 생성-성공")
    void create() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);

        when(stopoverRepository.save(Mockito.any(Stopover.class))).thenReturn(stopover);

        StopoverRequest request = new StopoverRequest(1, "김해시청", 1);
        stopoverService.create(request);

        Mockito.verify(stopoverRepository, Mockito.times(1)).save(Mockito.any(Stopover.class));
    }

    @Test
    @DisplayName("경유지 생성-이름중복")
    void create_fail() {
        when(stopoverRepository.existsByLocalAndStopover(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);

        StopoverRequest request = new StopoverRequest(1, "김해시청", 1);
        assertThrows(StopoverException.class, () -> stopoverService.create(request));

        Mockito.verify(stopoverRepository, Mockito.never()).save(Mockito.any(Stopover.class));
    }

    @Test
    @DisplayName("경유지 조회(경유지번호)")
    void getStopover() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);
        when(stopoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stopover));

        StopoverResponse response = stopoverService.getStopover(1L);

        assertNotNull(response);
        assertEquals("김해시청", response.getStopoverName());

        verify(stopoverRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("경유지 조회(경유지번호)-404")
    void getStopover_not_found() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);
        when(stopoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(StopoverException.class, () -> stopoverService.getStopover(1L));
    }

    @Test
    @DisplayName("경유지 조회(지자체)")
    void getStopoverList() {
        List<StopoverResponse> newResponseList = List.of(
                new StopoverResponse(1L, 1, "김해시청", 2),
                new StopoverResponse(2L, 1, "인제대", 1)
        );
        when(stopoverRepository.findStopoverList(Mockito.anyInt())).thenReturn(newResponseList);

        List<StopoverResponse> responseList = stopoverService.getStopoverList(1);
        assertEquals(2, responseList.size());

        verify(stopoverRepository, Mockito.times(1)).findStopoverList(Mockito.anyInt());
    }

    @Test
    @DisplayName("경유지 변경")
    void update() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);
        when(stopoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stopover));

        StopoverUpdateRequest request = new StopoverUpdateRequest(1L, 1, "김해대");
        StopoverResponse response = stopoverService.update(request);
        assertEquals(1L, response.getStopoverNo());
        assertEquals("김해대", response.getStopoverName());

        verify(stopoverRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("경유지 변경-이름 중복")
    void update_conflict() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);
        when(stopoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stopover));
        when(stopoverRepository.existsByLocalAndStopover(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);

        StopoverUpdateRequest request = new StopoverUpdateRequest(1L, 1, "인제대");
        assertThrows(StopoverException.class, () -> stopoverService.update(request));

        verify(stopoverRepository, Mockito.times(1)).findById(Mockito.anyLong());
        verify(stopoverRepository, Mockito.times(1)).existsByLocalAndStopover(Mockito.anyInt(), Mockito.anyString());
    }

    @Test
    @DisplayName("경유지 순서 변경")
    void updateOrder() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);
        when(stopoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stopover));

        StopoverUpdateOrderRequest request = new StopoverUpdateOrderRequest(1L, 3);
        StopoverResponse response = stopoverService.updateOrder(request);
        assertEquals(3, response.getStopoverOrder());

        verify(stopoverRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("경유지 삭제")
    void delete() {
        Stopover stopover = Stopover.ofNewStopover(1, "김해시청", 1);
        ReflectionTestUtils.setField(stopover, "stopoverNo", 1L);
        when(stopoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stopover));

        stopoverService.delete(1L);

        verify(stopoverRepository, Mockito.times(1)).findById(Mockito.anyLong());

        Optional<Stopover> deleteStopover = stopoverRepository.findById(1L);
        assertNotNull(deleteStopover);
        assertTrue(deleteStopover.get().isStopoverDeletion());
    }

    @Test
    @DisplayName("경유지 삭제(지자체)")
    void deleteAll() {
        Stopover stopover1 = Stopover.ofNewStopover( 1, "김해시청", 2);
        Stopover stopover2 = Stopover.ofNewStopover(1, "인제대", 1);

        when(stopoverRepository.findAllByLocalNo(Mockito.anyInt())).thenReturn(List.of(stopover1, stopover2));

        stopoverService.deleteAll(1);

        verify(stopoverRepository, Mockito.times(1)).findAllByLocalNo(Mockito.anyInt());

        List<Stopover> responseList = stopoverRepository.findAllByLocalNo(1);
        assertEquals(2, responseList.size());
        assertTrue(responseList.getFirst().isStopoverDeletion());
        assertTrue(responseList.get(1).isStopoverDeletion());
    }
}