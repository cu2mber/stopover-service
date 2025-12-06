package com.cu2mber.stopoverservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 경유지(Stopover)의 순서 변경(Update Order) 이후
 * 클라이언트에 반환되는 응답 DTO입니다.
 * <p>
 * 변경된 경유지의 기본 정보와 새 순서값을 제공합니다.
 * 정렬 결과를 즉시 화면에 반영해야 하는 UI 요구사항을 대응하기 위해 사용됩니다.
 */
@Getter
@ToString
@NoArgsConstructor
public class StopoverUpdateOrderResponse {

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
     * 경유지 이름.
     */
    @JsonProperty("name")
    String stopoverName;

    /**
     * 변경된 경유지의 순서.
     * <p>
     * 클라이언트는 이 값을 기반으로 화면상의 정렬을 바로 반영할 수 있습니다.
     */
    @JsonProperty("sequence")
    int stopoverSequence;
}
