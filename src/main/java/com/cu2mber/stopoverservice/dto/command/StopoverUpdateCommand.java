package com.cu2mber.stopoverservice.dto.command;

public record StopoverUpdateCommand(
        Long stopoverNo,

        Long memberLocalNo,

        String stopoverName
) {
}
