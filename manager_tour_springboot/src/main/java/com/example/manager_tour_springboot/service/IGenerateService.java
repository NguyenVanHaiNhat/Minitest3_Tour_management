package com.example.manager_tour_springboot.service;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T>findAll();
    T save (T t);
    Optional<T>findById(Long id);
    void remove(Long id);
}
