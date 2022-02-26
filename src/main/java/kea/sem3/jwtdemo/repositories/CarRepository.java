package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> getCarByModelAndReservations(String model, int reservationId);
}
