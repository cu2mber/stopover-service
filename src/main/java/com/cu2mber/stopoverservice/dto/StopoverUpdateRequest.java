package com.cu2mber.stopoverservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StopoverUpdateRequest {

    int localNo;

    @JsonProperty("name")
    String stopoverName;
}
