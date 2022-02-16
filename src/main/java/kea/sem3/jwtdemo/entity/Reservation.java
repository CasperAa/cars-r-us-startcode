package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reservation {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    int id;

    private LocalDate rentalDate;

    //Administrative
    @CreationTimestamp
    private LocalDateTime reservationDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedTo;

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

    public LocalDateTime getReservationDate() {
        return reservationDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && Objects.equals(reservationDate, that.reservationDate) && Objects.equals(rentalDate, that.rentalDate) && Objects.equals(lastEdited, that.lastEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationDate, rentalDate, lastEdited);
    }




}
