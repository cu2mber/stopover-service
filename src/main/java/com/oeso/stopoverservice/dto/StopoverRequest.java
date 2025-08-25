package com.oeso.stopoverservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StopoverRequest {

    int localNo;

    @NotBlank(message = "회의실 번호는 필수입니다.")
    String name;

    int order;
}
