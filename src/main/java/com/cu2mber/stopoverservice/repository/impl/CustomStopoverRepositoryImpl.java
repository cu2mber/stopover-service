package com.cu2mber.stopoverservice.repository.impl;

import com.cu2mber.stopoverservice.domain.QStopover;
import com.cu2mber.stopoverservice.dto.response.QStopoverResponse;
import com.cu2mber.stopoverservice.dto.response.QStopoverSummaryResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.response.StopoverSummaryResponse;
import com.cu2mber.stopoverservice.repository.CustomStopoverRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomStopoverRepositoryImpl implements CustomStopoverRepository {
    private final JPAQueryFactory queryFactory;

    QStopover qStopover = QStopover.stopover;


    @Override
    public boolean existsByLocalAndStopover(Long memberLocalNo, String stopoverName) {
        String normalized  = stopoverName.replaceAll("\\s+", "").trim();

        Long exist = queryFactory.select(qStopover.count())
                    .from(qStopover)
                    .where(
                            qStopover.memberLocalNo.eq(memberLocalNo),
                            qStopover.stopoverNameNormalized.eq(normalized),
                            qStopover.stopoverDeletion.isFalse()
                    )
                    .fetchOne();

        return exist != null && exist > 0;
    }

    @Override
    public Page<StopoverSummaryResponse> findStopoverPage(Pageable pageable) {
        List<StopoverSummaryResponse> list = queryFactory.select(new QStopoverSummaryResponse(
                        qStopover.stopoverNo,
                        qStopover.memberLocalNo))
                .from(qStopover)
                .orderBy(qStopover.stopoverSequence.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select((qStopover.count()))
                .from(qStopover);

        return PageableExecutionUtils.getPage(list, pageable, count::fetchOne);
    }

    @Override
    public List<StopoverResponse> findStopoverList(Long memberLocalNo) {
        return queryFactory.select(new QStopoverResponse(
                        qStopover.stopoverNo,
                        qStopover.memberLocalNo,
                        qStopover.stopoverName,
                        qStopover.stopoverSequence))
                .from(qStopover)
                .where(
                        qStopover.memberLocalNo.eq(memberLocalNo),
                        qStopover.stopoverDeletion.isFalse()
                )
                .orderBy(qStopover.stopoverSequence.asc())
                .fetch();
    }

    @Override
    public Optional<Integer> findMaxSequenceByLocalNo(Long memberLocalNo) {
        return Optional.ofNullable(queryFactory
                .select(qStopover.stopoverSequence.max())
                .from(qStopover)
                .where(
                        qStopover.memberLocalNo.eq(memberLocalNo),
                        qStopover.stopoverDeletion.isFalse()
                )
                .fetchOne());
    }


}
