package com.cu2mber.stopoverservice.service;

import com.cu2mber.stopoverservice.dto.PageResult;
import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateOrderCommand;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverSummaryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 경유지(Stopover) 관리를 위한 서비스 인터페이스입니다.
 * <p>
 * 경유지 생성, 조회, 목록 조회, 순서 변경, 수정, 삭제 등
 * 전체 라이프사이클을 처리하는 기능을 제공합니다.
 */
public interface StopoverService {

    /**
     * 새로운 경유지를 생성합니다.
     *
     * @param command 경유지 생성에 필요한 요청 데이터
     * @return 생성된 경유지 정보
     */
    StopoverResponse create(StopoverCreateCommand command);

    /**
     * 경유지 단건을 조회합니다.
     *
     * @param stopoverNo 조회할 경유지 번호
     * @return 조회된 경유지 정보
     */
    StopoverResponse getStopover(Long stopoverNo);

    /**
     * 경유지 목록을 페이징 방식으로 조회합니다.
     *
     * @param pageable 페이지 번호, 크기, 정렬 조건
     * @return 페이징 처리된 경유지 요약 목록
     */
    PageResult<StopoverSummaryResponse> getStopoverPage(Pageable pageable);

    /**
     * 특정 지역(memberLocalNo)에 속한 모든 경유지 목록을 조회합니다.
     *
     * @param memberLocalNo 지역 식별 번호
     * @return 경유지 전체 목록
     */
    List<StopoverResponse> getStopoverList(Long memberLocalNo);

    /**
     * 경유지 정보를 수정합니다.
     *
     * @param command 수정 요청 데이터
     * @return 수정된 경유지 정보
     */
    StopoverResponse update(StopoverUpdateCommand command);

    /**
     * 경유지 순서를 일괄 수정합니다.
     *
     * @param command 순서 변경 요청 데이터
     */
    void updateOrder(StopoverUpdateOrderCommand command);

    /**
     * 경유지를 삭제합니다.
     *
     * @param stopoverNo 삭제할 경유지 번호
     */
    void delete(Long stopoverNo);

    /**
     * 특정 지역(memberLocalNo)에 속한 모든 경유지를 삭제합니다.
     *
     * @param memberLocalNo 지역 식별 번호
     */
    void deleteAll(Long memberLocalNo);
}
