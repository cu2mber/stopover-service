package com.cu2mber.stopoverservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StopoverUpdateOrderRequest(

        @JsonProperty("no")
        Long stopoverNo,

        @JsonProperty("sequence")
        int stopoverSequence
) {}