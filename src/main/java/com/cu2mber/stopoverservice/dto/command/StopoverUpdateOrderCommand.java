package com.cu2mber.stopoverservice.dto.command;

import java.util.List;

public record StopoverUpdateOrderCommand(
        Long memberLocalNo,
        List<UpdateOrderInfo> info
) {
        public record UpdateOrderInfo(
                Long stopoverNo,
                int stopoverSequence
        ) {}
}