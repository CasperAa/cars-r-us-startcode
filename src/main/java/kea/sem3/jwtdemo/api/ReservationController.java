package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.service.CarService;
import kea.sem3.jwtdemo.service.MemberService;
import kea.sem3.jwtdemo.service.ReservationService;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("api/reservations")
    public class ReservationController {
        ReservationService reservationService;

        public ReservationController(ReservationService reservationService){
            this.reservationService = reservationService;
        }

        @PutMapping("/{username}/reservations/{carId}")
        public ReservationResponse createReservation(@RequestBody ReservationRequest body, @PathVariable String username, @PathVariable int carId){
            return reservationService.createReservation(body, carId, username);
        }




    }
