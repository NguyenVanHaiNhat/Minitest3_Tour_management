package com.example.manager_tour_springboot.controller.posman;

import com.example.manager_tour_springboot.model.Tour;
import com.example.manager_tour_springboot.model.Type;
import com.example.manager_tour_springboot.service.tour.ITourService;
import com.example.manager_tour_springboot.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/tour")
@CrossOrigin("*")
public class ToursController {
    @Autowired
    private ITourService iTourService;
    @Autowired
    private ITypeService iTypeService;

    @ModelAttribute("type")
    public Iterable<Type> listType() {
        return iTypeService.findAll();
    }

    @GetMapping
    public ResponseEntity<Iterable<Tour>> findAllTour() {
        Iterable<Tour> tours = iTourService.findAll();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tour> saveTour(@RequestBody Tour tour) {
        return new ResponseEntity<>(iTourService.save(tour), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> findByIdTour(@PathVariable Long id) {
        Optional<Tour> tourOptional = iTourService.findById(id);
        return new ResponseEntity<>(tourOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour) {
        Optional<Tour> tourOptional = iTourService.findById(id);
        tour.setId(tourOptional.get().getId());
        return new ResponseEntity<>(iTourService.save(tour), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tour> deleteTour(@PathVariable Long id) {
        Optional<Tour> tourOptional = iTourService.findById(id);
        if (!tourOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTourService.remove(id);
        return new ResponseEntity<>(tourOptional.get(), HttpStatus.OK);

    }
}
