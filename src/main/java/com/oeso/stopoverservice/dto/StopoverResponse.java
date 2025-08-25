package com.oeso.stopoverservice.dto;

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

    String stopoverName;

    int stopoverOrder;

}
