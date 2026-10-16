package com.realm.catalog_service.seat.entity;

import com.realm.catalog_service.seat.enums.SeatStatus;
import com.realm.catalog_service.show.entity.ShowEntity;
import com.realm.catalog_service.utils.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats",
        schema = "catalog",
        uniqueConstraints = @UniqueConstraint(columnNames = {"show_id", "row_label", "seat_number"}),
        indexes = @Index(columnList = "show_id"))
public class SeatEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id")
    private ShowEntity show;

    @Column(nullable = false)
    private String rowLabel;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatStatus status = SeatStatus.AVAILABLE;

    @Column(nullable = false)
    private BigDecimal price;

    @Version
    private Long version;
}
