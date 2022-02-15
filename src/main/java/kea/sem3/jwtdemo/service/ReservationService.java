package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
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

    public void makeReservation(int carID, String username){
        Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found"));
        Car car = carRepository.findById(carID).orElseThrow(()->new Client4xxException("Car not found"));




    }
}
