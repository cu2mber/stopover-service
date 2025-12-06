package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 경유지(Stopover) 조회 및 검증을 위한 커스텀 Repository 인터페이스입니다.
 * <p>
 * Spring Data JPA 기본 제공 기능 외에,
 * 지역 기반 중복 체크, 페이지 조회, 리스트 조회, 최대 순서 조회 등
 * 도메인 요구사항에 맞춘 커스텀 쿼리 기능을 정의합니다.
 */
public interface CustomStopoverRepository {

    /**
     * 특정 지역(memberLocalNo)에 동일한 경유지 이름이 존재하는지 확인합니다.
     * <p>
     * 정규화된 비교(공백 제거, 대소문자 무시 등) 전략은 구현체에서 처리합니다.
     *
     * @param memberLocalNo 지역 식별 번호
     * @param stopoverName  확인할 경유지 이름
     * @return 중복 경유지가 존재하면 {@code true}, 존재하지 않으면 {@code false}
     */
    boolean existsByLocalAndStopover(Long memberLocalNo, String stopoverName);

    /**
     * 경유지 목록을 페이징 방식으로 조회합니다.
     * <p>
     * 요약 정보만 필요할 때 사용되며,
     * 페이지 번호, 페이지 크기, 정렬 기준은 {@link Pageable}을 통해 전달합니다.
     *
     * @param pageable 페이지 요청 정보
     * @return 페이징 처리된 경유지 요약 정보 목록
     */
    Page<StopoverSummaryResponse> findStopoverPage(Pageable pageable);

    /**
     * 특정 지역(memberLocalNo)에 속한 모든 경유지 목록을 조회합니다.
     * <p>
     * 순서 기반 정렬(예: stopoverSequence) 등의 조건은
     * 구현체에서 비즈니스 규칙에 따라 적용합니다.
     *
     * @param memberLocalNo 지역 식별 번호
     * @return 해당 지역의 경유지 전체 목록
     */
    List<StopoverResponse> findStopoverList(Long memberLocalNo);

    /**
     * 특정 지역(memberLocalNo)에 속한 경유지 중
     * 가장 큰 순서(stopoverSequence)를 조회합니다.
     * <p>
     * 신규 경유지 생성 시 다음 순서를 계산할 때 주로 사용됩니다.
     *
     * @param memberLocalNo 지역 식별 번호
     * @return 최대 순서 값 (경유지가 없으면 {@code Optional.empty()})
     */
    Optional<Integer> findMaxSequenceByLocalNo(Long memberLocalNo);
}

