package com.example.manager_tour_springboot.controller.posman;

import com.example.manager_tour_springboot.model.Type;
import com.example.manager_tour_springboot.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/type")
@CrossOrigin("*")
public class TypesController {
    @Autowired
    private ITypeService iTypeService;

    @GetMapping
    public ResponseEntity<Iterable<Type>> findAllType() {
        Iterable<Type> types = iTypeService.findAll();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> saveType(@RequestBody Type type) {
        return new ResponseEntity<>(iTypeService.save(type), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> findByIdType(@PathVariable Long id) {
        Optional<Type> typeOptional = iTypeService.findById(id);
        return new ResponseEntity<>(typeOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type type) {
        Optional<Type> typeOptional = iTypeService.findById(id);
        type.setId(typeOptional.get().getId());
        return new ResponseEntity<>(iTypeService.save(type), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable Long id) {
        Optional<Type> typeOptional = iTypeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTypeService.remove(id);
        return new ResponseEntity<>(typeOptional.get(), HttpStatus.OK);

    }
}
