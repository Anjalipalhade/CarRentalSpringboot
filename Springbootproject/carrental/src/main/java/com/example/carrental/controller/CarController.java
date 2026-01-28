package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // 1️⃣ Display all cars on home page
    @GetMapping("/")
    public String getAllCars(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "index"; // maps to templates/index.html
    }

    // 2️⃣ Show form to add a new car
    @GetMapping("/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "add-car"; // maps to templates/add-car.html
    }

    // 3️⃣ Save new car to MongoDB
    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/cars/"; // after saving, redirect to home page
    }

    // 4️⃣ Delete a car by ID
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable String id) {
        carRepository.deleteById(id);
        return "redirect:/cars/";
    }
}
