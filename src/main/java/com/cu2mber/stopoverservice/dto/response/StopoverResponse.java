package com.cu2mber.stopoverservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class StopoverResponse {

    @JsonProperty("no")
    Long stopoverNo;

    Long memberLocalNo;

//    String localName;

    @JsonProperty("name")
    String stopoverName;

    @JsonProperty("order")
    int stopoverOrder;

    @QueryProjection
    public StopoverResponse(Long stopoverNo, Long memberLocalNo, String stopoverName, int stopoverOrder) {
        this.stopoverNo = stopoverNo;
        this.memberLocalNo = memberLocalNo;
        this.stopoverName = stopoverName;
        this.stopoverOrder = stopoverOrder;
    }
}
