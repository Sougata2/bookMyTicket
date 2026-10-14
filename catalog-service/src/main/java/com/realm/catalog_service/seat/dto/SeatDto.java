package com.realm.catalog_service.seat.dto;

import com.realm.catalog_service.seat.enums.SeatStatus;
import com.realm.catalog_service.show.dto.ShowDto;
import com.realm.catalog_service.utils.AuditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.realm.catalog_service.seat.entity.SeatEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto extends AuditDto implements Serializable {
    private Long id;
    private ShowDto show;
    private String rowLabel;
    private int seatNumber;
    private SeatStatus status;
    private BigDecimal price;
    private Long version;
}