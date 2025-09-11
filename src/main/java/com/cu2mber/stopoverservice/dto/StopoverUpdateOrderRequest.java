package com.cu2mber.stopoverservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StopoverUpdateOrderRequest {

    @JsonProperty("no")
    Long stopoverNo;

    @JsonProperty("order")
    int stopoverOrder;
}
