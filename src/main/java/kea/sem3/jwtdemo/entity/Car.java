package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.CarRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //Fordi det er en Entitiy classe, skal den have en @entity notation, id og en tom public constructor metode.
    public Car() {
    }

    public Car(CarBrand brand, String model, double pricePrDay, double discount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = discount;
    }

    public Car(CarRequest body) {
        this.brand = body.getBrand();
        this.model = body.getModel();
        this.pricePrDay = body.getPricePrDay();
        this.bestDiscount = body.getBestDiscount();
    }
    //Add fetch = FetchType.EAGER to mappedby, if problems related to transactional appear.
    @OneToMany(mappedBy = "reservedCar", fetch = FetchType.EAGER)
    private Set <Reservation> reservations = new HashSet<>();

    public void addReservation (Reservation newReservation){
        reservations.add(newReservation);
    }

    @Enumerated(EnumType.STRING)
    CarBrand brand;

    @Column(length = 60)
    String model;

    double pricePrDay;

    //Best discount price (percent fo pricePrDay) an admin can offer
    double bestDiscount;

    @CreationTimestamp
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime edited;


}
