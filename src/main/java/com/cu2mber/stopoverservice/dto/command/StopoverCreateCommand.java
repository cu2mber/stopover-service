package com.cu2mber.stopoverservice.dto.command;

/**
 * 새로운 경유지(Stopover)를 생성하기 위한 Command 객체입니다.
 * <p>
 * 컨트롤러에서 서비스 계층으로 전달되는 입력 데이터로,
 * 경유지가 속할 지역 식별 번호와 경유지 이름을 포함합니다.
 *
 * @param memberLocalNo  경유지가 속할 지역(memberLocal)의 식별 번호
 * @param stopoverName   생성할 경유지 이름
 */
public record StopoverCreateCommand (
        Long memberLocalNo,

        String stopoverName
) {
}
