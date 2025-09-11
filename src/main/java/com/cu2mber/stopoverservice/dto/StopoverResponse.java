package com.cu2mber.stopoverservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class StopoverResponse {

    Long stopoverNo;

    int localNo;

//    String localName;

    @JsonProperty("name")
    String stopoverName;

    @JsonProperty("order")
    int stopoverOrder;

    @QueryProjection
    public StopoverResponse(Long stopoverNo, int localNo, String stopoverName, int stopoverOrder) {
        this.stopoverNo = stopoverNo;
        this.localNo = localNo;
        this.stopoverName = stopoverName;
        this.stopoverOrder = stopoverOrder;
    }
}
