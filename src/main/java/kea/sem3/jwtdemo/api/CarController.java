package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable int id) throws Exception {
        return carService.getCar(id, false);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Added for week 2 handin
    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCar(body, id);
    }

    @PatchMapping("/{id}/{newPrice}")
    @RolesAllowed("ADMIN")
    public void editPrice(@PathVariable int id, @PathVariable double newPrice) {
        carService.updatePrice(id, newPrice);
    }

    //Added for week 2 handin
    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }


}

