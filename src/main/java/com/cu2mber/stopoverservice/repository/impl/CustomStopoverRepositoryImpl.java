package com.cu2mber.stopoverservice.repository.impl;

import com.cu2mber.stopoverservice.domain.QStopover;
import com.cu2mber.stopoverservice.dto.QStopoverResponse;
import com.cu2mber.stopoverservice.dto.StopoverResponse;
import com.cu2mber.stopoverservice.repository.CustomStopoverRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomStopoverRepositoryImpl implements CustomStopoverRepository {
    private final JPAQueryFactory queryFactory;

    QStopover qStopover = QStopover.stopover;


    @Override
    public boolean existsByLocalAndStopover(int localNo, String stopoverName) {
        Long exist = queryFactory.select(qStopover.count())
                    .from(qStopover)
                    .where(qStopover.localNo.eq(localNo), qStopover.stopoverName.eq(stopoverName))
                    .fetchOne();

        return exist != null && exist > 0;
    }

    @Override
    public List<StopoverResponse> findStopoverList(int localNo) {
        return queryFactory.select(new QStopoverResponse(
                        qStopover.stopoverNo,
                        qStopover.localNo,
                        qStopover.stopoverName,
                        qStopover.stopoverOrder))
                .from(qStopover)
                .where(qStopover.localNo.eq(localNo), qStopover.stopoverDeletion.isFalse())
                .fetch();
    }


}
