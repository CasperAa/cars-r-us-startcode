package kea.sem3.jwtdemo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Reservation {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedTo;


    //Administrative
    @CreationTimestamp
    private LocalDate reservationDate;
    private LocalDate rentalDate;

    //Administrative
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Reservation() {}

    public Reservation(LocalDate rentalDate, Car reservedCar, Member reservedTo) {
        this.rentalDate = rentalDate;
        this.reservedCar = reservedCar;
        this.reservedTo = reservedTo;
        reservedCar.addReservation(this);
        reservedTo.addReservation(this);
    }

    public int getId() {
        return id;
    }

    public Car getReservedCar() {
        return reservedCar;
    }

    public void setReservedCar(Car reservedCar) {
        this.reservedCar = reservedCar;
    }

    public Member getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(Member reservedTo) {
        this.reservedTo = reservedTo;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", rentalDate=" + rentalDate +
                ", lastEdited=" + lastEdited +
                '}';
    }

}
