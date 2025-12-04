package com.cu2mber.stopoverservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class StopoverUpdateOrderResponse {

    @JsonProperty("no")
    Long stopoverNo;

    Long memberLocalNo;

//    String localName;

    @JsonProperty("name")
    String stopoverName;

    @JsonProperty("sequence")
    int stopoverSequence;
}
