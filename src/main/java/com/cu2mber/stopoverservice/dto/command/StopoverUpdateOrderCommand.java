package com.cu2mber.stopoverservice.dto.command;

import java.util.List;

/**
 * 경유지(Stopover)의 순서를 일괄 수정하기 위한 Command 객체입니다.
 * <p>
 * 컨트롤러에서 서비스 계층으로 전달되는 입력 데이터로,
 * 사용자의 특정 지역(memberLocalNo)에 속한 여러 경유지의 순서를
 * 한 번의 요청으로 변경할 때 사용됩니다.
 * 클라이언트에서 전달된 순서 정보는
 * {@link UpdateOrderInfo} 리스트 형태로 포함됩니다.
 *
 * @param memberLocalNo 사용자의 지역 식별 번호
 * @param info          수정할 경유지 번호와 새로운 순서 정보를 담은 리스트
 */
public record StopoverUpdateOrderCommand(
        Long memberLocalNo,
        List<UpdateOrderInfo> info
) {

        /**
         * 개별 경유지의 순서 변경 정보를 나타내는 레코드입니다.
         * <p>
         * 특정 경유지(stopoverNo)의 새 순서(stopoverSequence)를 지정합니다.
         * 이 정보는 {@link StopoverUpdateOrderCommand#info()}에 포함되어
         * 일괄 업데이트의 요소로 사용됩니다.
         *
         * @param stopoverNo        순서를 변경할 경유지 식별 번호
         * @param stopoverSequence  변경할 새로운 경유지 순서 값
         */
        public record UpdateOrderInfo(
                Long stopoverNo,
                int stopoverSequence
        ) {}
}
