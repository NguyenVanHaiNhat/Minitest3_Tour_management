package com.example.manager_tour_springboot.repo;

import com.example.manager_tour_springboot.model.Tour;
import org.springframework.data.repository.CrudRepository;

public interface ITourRepository extends CrudRepository<Tour,Long> {
}
