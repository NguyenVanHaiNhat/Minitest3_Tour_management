package com.example.manager_tour_springboot.service.tour;

import com.example.manager_tour_springboot.model.Tour;
import com.example.manager_tour_springboot.repo.ITourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourService implements ITourService {
    @Autowired
    private ITourRepository iTourRepository;

    @Override
    public Iterable<Tour> findAll() {
        return iTourRepository.findAll();
    }

    @Override
    public Tour save(Tour tour) {
        return iTourRepository.save(tour);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return iTourRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iTourRepository.deleteById(id);
    }
}
