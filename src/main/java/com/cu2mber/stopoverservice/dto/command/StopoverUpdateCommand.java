package com.cu2mber.stopoverservice.dto.command;

/**
 * 경유지(Stopover) 정보를 수정하기 위한 Command 객체입니다.
 * <p>
 * 컨트롤러에서 서비스 계층으로 전달되는 입력 데이터로,
 * 수정 대상 경유지 번호와 변경할 지역 정보 및 경유지 이름을 포함합니다.
 *
 * @param stopoverNo      수정할 경유지의 식별 번호
 * @param memberLocalNo   변경할 지역(memberLocal) 식별 번호
 * @param stopoverName    변경할 경유지 이름
 */
public record StopoverUpdateCommand(
        Long stopoverNo,

        Long memberLocalNo,

        String stopoverName
) {
}
