package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public  void makeData(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);
        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);


        Member member1 = memberRepository.save(new Member("KW","kw@a.dk","test12","Kurt","Wonnegut","Lyngbyvje 34","Lyngby","2800"));
        Member member2 = memberRepository.save(new Member("HW","hw@a.dk","test12","Hanne","Wonnegut","Lyngbyvje 34","Lyngby","2800"));

        Car carVolvo = carRepository.save(new Car(CarBrand.VOLVO, "C40", 560,10));
        carRepository.save(new Car(CarBrand.VOLVO, "V70", 500,10));
        carRepository.save(new Car(CarBrand.VOLVO, "V49", 400,10));
        carRepository.save(new Car(CarBrand.SUZUKI, "Vitara", 500,14));
        carRepository.save(new Car(CarBrand.SUZUKI, "Vitara", 500,14));
        carRepository.save(new Car(CarBrand.SUZUKI, "S-Cross", 500,14));

        //Make reservation data
        Reservation res1 = new Reservation(LocalDate.of(2022, 3,1), carVolvo, member1);
        reservationRepository.save(res1);

        Reservation res = reservationRepository.findReservationByReservedCar_IdAndRentalDate(
                carVolvo.getId(),(LocalDate.of(2022,3,1)));
        if(res == null){
            Reservation res2 = new Reservation(LocalDate.of(2022, 3,1), carVolvo, member2);
            reservationRepository.save(res2);
        } else {
            System.out.println("Car is already reserved");
        }


        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();

        makeData();

    }
}
