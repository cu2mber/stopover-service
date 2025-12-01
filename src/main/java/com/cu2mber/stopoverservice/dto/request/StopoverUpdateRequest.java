package com.cu2mber.stopoverservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StopoverUpdateRequest(
        int localNo,

        @JsonProperty("name")
        String stopoverName
) {


}
