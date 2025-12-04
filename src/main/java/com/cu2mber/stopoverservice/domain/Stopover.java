package com.cu2mber.stopoverservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "stopover")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Stopover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stopover_no")
    private Long stopoverNo;

    @Column(name = "member_local_no", columnDefinition = "bigint", nullable = false)
    @Comment("지자체 멤버 번호")
    private Long memberLocalNo;

    @Column(name = "stopover_name", length = 255, nullable = false)
    @Comment("경유지이름")
    private String stopoverName;

    @Column(name = "stopover_name_normalized", length = 255,nullable = false)
    private String stopoverNameNormalized;

    @Column(name = "stopover_sequence", columnDefinition = "tinyint", nullable = false)
    @Comment("순서")
    private Integer stopoverSequence;

    @Column(name = "stopover_deletion", columnDefinition = "tinyint", nullable = true)
    @Comment("삭제여부")
    private boolean stopoverDeletion;

    @Column(name = "deleted_at", nullable = true)
    @Comment("삭제일시")
    private LocalDateTime deletedAt;

    private Stopover(Long memberLocalNo, String stopoverName, int stopoverSequence, boolean stopoverDeletion, LocalDateTime deletedAt){
        this.memberLocalNo = memberLocalNo;
        this.stopoverName = stopoverName;
        this.stopoverSequence = stopoverSequence;
        this.stopoverDeletion = stopoverDeletion;
        this.deletedAt = deletedAt;
    }

    public static Stopover ofNewStopover(Long memberLocalNo, String stopoverName, int sequence){
        return new Stopover(memberLocalNo, stopoverName, sequence, false, null);
    }

    public void normalizeName() {
        this.stopoverNameNormalized = this.stopoverName.replaceAll("\\s+", "").trim();
    }

    public void update(String stopoverName) {
        this.stopoverName = stopoverName;
    }

    public void updateOrder(int stopoverSequence){
        this.stopoverSequence = stopoverSequence;
    }

    public void delete(){
        this.stopoverDeletion = true;
        this.deletedAt = LocalDateTime.now();
    }

}
