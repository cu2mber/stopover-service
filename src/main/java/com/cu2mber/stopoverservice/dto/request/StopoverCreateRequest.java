package com.cu2mber.stopoverservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record StopoverCreateRequest(
        @NotBlank(message = "경유지 이름은 필수입니다.")
        @JsonProperty("name")
        String stopoverName
) {
}
