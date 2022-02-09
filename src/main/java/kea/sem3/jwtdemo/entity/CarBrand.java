package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public enum CarBrand{

    VOLVO("Volvo"),
    TOYOTA("Toyata"),
    WW("WW"),
    FORD("Ford"),
    SUZUKI("Suzuki");

    public final String printName;

    private CarBrand(String printName){
        this.printName = printName;
    }

    @CreationTimestamp
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime edited;

}