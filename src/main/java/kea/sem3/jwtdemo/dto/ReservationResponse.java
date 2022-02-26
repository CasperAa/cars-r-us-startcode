package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private LocalDate rentalDate;
    private LocalDate reservationDate;

    public ReservationResponse(Reservation reservation) {
        this.reservationDate = reservation.getReservationDate();
        this.rentalDate = reservation.getRentalDate();
    }
}