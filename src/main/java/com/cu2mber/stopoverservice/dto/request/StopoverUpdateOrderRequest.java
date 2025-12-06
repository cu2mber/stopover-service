package com.cu2mber.stopoverservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record StopoverUpdateOrderRequest(

        @NotNull(message = "경유지 식별 번호는 필수입니다.")
        @JsonProperty("no")
        Long stopoverNo,

        @NotNull(message = "경유지 순서를 입력해주세요.")
        @JsonProperty("sequence")
        int stopoverSequence
) {}