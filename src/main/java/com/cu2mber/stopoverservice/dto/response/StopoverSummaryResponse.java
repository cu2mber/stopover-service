package com.cu2mber.stopoverservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 경유지(Stopover) 목록 조회 시 사용되는 요약 응답 DTO입니다.
 * <p>
 * 상세 정보가 필요하지 않은 목록 화면 또는 페이징 조회에서
 * 최소한의 식별 정보만 제공하기 위해 사용됩니다.
 * <p>
 * Querydsl의 {@link QueryProjection}을 통해
 * 타입 안전한 DTO 매핑을 지원합니다.
 */
@Getter
@ToString
@NoArgsConstructor
public class StopoverSummaryResponse {

    /**
     * 경유지 식별 번호.
     */
    @JsonProperty("no")
    Long stopoverNo;

    /**
     * 경유지가 속한 지역(memberLocal)의 식별 번호.
     */
    Long memberLocalNo;

    // String localName;

    /**
     * Querydsl 기반 프로젝션을 위한 생성자입니다.
     *
     * @param stopoverNo     경유지 번호
     * @param memberLocalNo  지역(memberLocal) 번호
     */
    @QueryProjection
    public StopoverSummaryResponse(Long stopoverNo, Long memberLocalNo) {
        this.stopoverNo = stopoverNo;
        this.memberLocalNo = memberLocalNo;
    }
}
