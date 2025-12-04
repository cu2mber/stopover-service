package com.cu2mber.stopoverservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class StopoverSummaryResponse {

    @JsonProperty("no")
    Long stopoverNo;

    Long memberLocalNo;

//    String localName;

    @QueryProjection
    public StopoverSummaryResponse(Long stopoverNo, Long memberLocalNo) {
        this.stopoverNo = stopoverNo;
        this.memberLocalNo = memberLocalNo;
    }
}
