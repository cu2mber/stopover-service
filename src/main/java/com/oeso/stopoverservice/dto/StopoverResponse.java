package com.oeso.stopoverservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StopoverResponse {

    int localNo;

//    String localName;

    @JsonProperty("name")
    String stopoverName;

    @JsonProperty("order")
    int stopoverOrder;

}
