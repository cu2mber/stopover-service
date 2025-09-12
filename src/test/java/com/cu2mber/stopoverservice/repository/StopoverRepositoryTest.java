package com.cu2mber.stopoverservice.repository;

import com.cu2mber.stopoverservice.common.config.CommonConfig;
import com.cu2mber.stopoverservice.domain.Stopover;
import com.cu2mber.stopoverservice.dto.StopoverResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Import(CommonConfig.class)
@ActiveProfiles("test")
@DataJpaTest
class StopoverRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StopoverRepository stopoverRepository;

    Stopover stopover;
    Stopover stopover2;

    @BeforeEach
    void setUp() {
        stopover = Stopover.ofNewStopover(1, "김해시청", 2);
        stopover2 = Stopover.ofNewStopover(1, "인제대", 1);

        entityManager.persist(stopover);
        entityManager.persist(stopover2);
        entityManager.flush();
    }

    @Test
    @DisplayName("지자체에서 입력한 경유지가 있는 경우")
    void existsByLocalAndStopover_true() {
        boolean exist = stopoverRepository.existsByLocalAndStopover(1, "김해시청");
        assertTrue(exist);
    }

    @Test
    @DisplayName("지자체에서 입력한 경유지가 없는 경우")
    void existsByLocalAndStopover_false() {
        boolean exist = stopoverRepository.existsByLocalAndStopover(1, "장유터미널");
        assertFalse(exist);
    }

    @Test
    @DisplayName("경유지 리스트 찾기 - QueryDSL")
    void findStopoverList() {

        List<StopoverResponse> responses = stopoverRepository.findStopoverList(1);

        assertFalse(responses.isEmpty());

        assertAll(
                () -> {
                    assertEquals(2, responses.size());
                    assertEquals("인제대", responses.getFirst().getStopoverName());
                    assertEquals("김해시청", responses.get(1).getStopoverName());
                }
        );
    }

    @Test
    @DisplayName("경유지 리스트 찾기 - JPA")
    void findAllByLocalNo() {

        List<Stopover> responses = stopoverRepository.findAllByLocalNo(1);

        assertFalse(responses.isEmpty());

        assertAll(
                () -> {
                    assertEquals(2, responses.size());
                    assertEquals("김해시청", responses.getFirst().getStopoverName());
                    assertEquals("인제대", responses.get(1).getStopoverName());
                }
        );
    }
}