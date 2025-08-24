package com.oeso.stopoverservice.domain;

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

    @Column(name = "stopover_name", length = 255)
    @Comment("경유지이름")
    private String stopoverName;

    @Column(name = "stopover_order", columnDefinition = "tinyint")
    @Comment("순서")
    private int stopoverOrder;

    @Column(name = "stopover_deletion", columnDefinition = "tinyint")
    @Comment("삭제여부")
    private boolean stopoverDeletion;

    @Column(name = "deleted_at")
    @Comment("삭제일시")
    private LocalDateTime deleted_at;

    @Column(name = "local_no", columnDefinition = "tinyint")
    @Comment("지자체번호")
    private int localNo;

    public Stopover(int localNo, String stopoverName, int stopoverOrder, boolean stopoverDeletion, LocalDateTime deleted_at){
        this.localNo = localNo;
        this.stopoverName = stopoverName;
        this.stopoverOrder = stopoverOrder;
        this.stopoverDeletion = stopoverDeletion;
        this.deleted_at = deleted_at;
    }

    public static Stopover ofNewStopover(int localNo, String stopoverName, int stopoverOrder){
        return new Stopover(localNo, stopoverName, stopoverOrder, false, null);
    }

    public void delete(Long stopoverNo){
        this.stopoverNo = stopoverNo;
        this.stopoverDeletion = true;
        this.deleted_at = LocalDateTime.now();
    }

}
