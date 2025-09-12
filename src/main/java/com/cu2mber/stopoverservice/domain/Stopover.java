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

    @Column(name = "local_no", columnDefinition = "tinyint", nullable = false)
    @Comment("지자체번호")
    private int localNo;

    @Column(name = "stopover_name", length = 255, nullable = false)
    @Comment("경유지이름")
    private String stopoverName;

    @Column(name = "stopover_order", columnDefinition = "tinyint", nullable = false)
    @Comment("순서")
    private int stopoverOrder;

    @Column(name = "stopover_deletion", columnDefinition = "tinyint", nullable = true)
    @Comment("삭제여부")
    private boolean stopoverDeletion;

    @Column(name = "deleted_at", nullable = true)
    @Comment("삭제일시")
    private LocalDateTime deletedAt;

    private Stopover(int localNo, String stopoverName, int stopoverOrder, boolean stopoverDeletion, LocalDateTime deletedAt){
        this.localNo = localNo;
        this.stopoverName = stopoverName;
        this.stopoverOrder = stopoverOrder;
        this.stopoverDeletion = stopoverDeletion;
        this.deletedAt = deletedAt;
    }

    public static Stopover ofNewStopover(int localNo, String stopoverName, int stopoverOrder){
        return new Stopover(localNo, stopoverName, stopoverOrder, false, null);
    }

    public void update(String stopoverName) {
        this.stopoverName = stopoverName;
    }

    public void updateOrder(int stopoverOrder){
        this.stopoverOrder = stopoverOrder;
    }

    public void delete(){
        this.stopoverDeletion = true;
        this.deletedAt = LocalDateTime.now();
    }

}
