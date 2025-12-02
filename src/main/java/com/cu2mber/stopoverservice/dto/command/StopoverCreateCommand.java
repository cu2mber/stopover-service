package com.cu2mber.stopoverservice.dto.command;

public record StopoverCreateCommand (
        Long memberLocalNo,

        String stopoverName
) {
}
