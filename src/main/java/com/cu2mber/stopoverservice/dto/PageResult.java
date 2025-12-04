package com.cu2mber.stopoverservice.dto;

import java.util.List;

public record PageResult<T>(
        List<T> contents,
        long totalContent,
        int totalPage
) {
}
