package com.realm.catalog_service.screen.entity;

import com.realm.catalog_service.utils.AuditEntity;
import com.realm.catalog_service.venue.entity.VenueEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "screens", schema = "catalog")
public class ScreenEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "venue_id")
    private VenueEntity venue;

    @Column(nullable = false)
    private String name;

    private Integer totalRows;
    private Integer seatsPerRow;
}
