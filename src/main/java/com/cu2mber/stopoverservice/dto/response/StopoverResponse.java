package com.cu2mber.stopoverservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 경유지(Stopover) 정보를 클라이언트에 반환하기 위한 응답 DTO입니다.
 * <p>
 * 단건 조회, 목록 조회, 정렬 결과 반환 등 다양한 응답에서 사용되며,
 * Jackson {@link JsonProperty}를 통해 클라이언트에 전달되는 필드명을 명확히 지정합니다.
 * <p>
 * 또한 Querydsl의 {@link QueryProjection}을 사용하여
 * 타입 안전한 DTO 매핑을 지원합니다.
 */
@Getter
@ToString
@NoArgsConstructor
public class StopoverResponse {

    /**
     * 경유지 식별 번호.
     */
    @JsonProperty("no")
    Long stopoverNo;

    /**
     * 경유지가 속한 지역(memberLocal)의 식별 번호.
     */
    Long memberLocalNo;

    /**
     * 경유지가 속한 지역(memberLocal)의 이름.
     */
    @Setter
    @JsonProperty("local")
     String localName;

    /**
     * 경유지 이름.
     */
    @JsonProperty("name")
    String stopoverName;

    /**
     * 경유지 순서.
     * <p>
     * 경유지 리스트에서의 노출 순서 또는 경유지 이동 동작 시의 정렬 기준으로 사용됩니다.
     */
    @JsonProperty("sequence")
    int stopoverSequence;

    /**
     * 경유지가 생성된 일자.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    /**
     * Querydsl에서 생성자 기반 프로젝션을 위해 사용되는 생성자입니다.
     *
     * @param stopoverNo        경유지 번호
     * @param memberLocalNo     지역(memberLocal) 식별 번호
     * @param stopoverName      경유지 이름
     * @param stopoverSequence  경유지 순서
     */
    @QueryProjection
    public StopoverResponse(Long stopoverNo, Long memberLocalNo, String stopoverName, int stopoverSequence, LocalDateTime createdAt) {
        this.stopoverNo = stopoverNo;
        this.memberLocalNo = memberLocalNo;
        this.stopoverName = stopoverName;
        this.stopoverSequence = stopoverSequence;
        this.createdAt = createdAt;
    }
}
