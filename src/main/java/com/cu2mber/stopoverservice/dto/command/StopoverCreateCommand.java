package com.cu2mber.stopoverservice.dto.command;

public record StopoverCreateCommand (
        Long memberNo,

        int localNo,

        String stopoverName
) {
}
