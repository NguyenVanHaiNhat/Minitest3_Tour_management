package com.example.manager_tour_springboot.repo;

import com.example.manager_tour_springboot.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface ITypeRepository extends CrudRepository<Type,Long> {
}
