package com.realm.catalog_service.show.entity;

import com.realm.catalog_service.event.entity.EventEntity;
import com.realm.catalog_service.screen.entity.ScreenEntity;
import com.realm.catalog_service.utils.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shows",
        schema = "catalog",
        indexes = {
                @Index(columnList = "event_id"),
                @Index(columnList = "startTime")
        })
public class ShowEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne(optional = false)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screen;

    @Column(nullable = false)
    private Instant startTime;

    @Column(nullable = false)
    private BigDecimal basePrice;
}
