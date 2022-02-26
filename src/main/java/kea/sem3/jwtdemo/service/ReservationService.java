package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse createReservation(ReservationRequest body, int carId, String username) {
        //Create a new reservation entity to add to the database
        Reservation reservation = new Reservation(body.getRentalDate(), body.getReservedCar(), body.getMember());

        //Locate specific car and member to add to reservation
        Car car = carRepository.findById(carId).orElseThrow();
        Member member = memberRepository.findById(username).orElseThrow();

        //Set attributes to the value from the database
        reservation.setReservedCar(car);
        reservation.setReservedTo(member);

        //Add reservation to member and car list, and create the reservation
        member.getReservations().add(reservation);
        car.getReservations().add(reservation);
        final Reservation newReservation = reservationRepository.save(reservation);
        return new ReservationResponse(newReservation);
    }

}
