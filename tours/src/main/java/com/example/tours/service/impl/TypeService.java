package com.example.tours.service.impl;

import com.example.tours.model.Type;
import com.example.tours.repository.TypeRepository;
import com.example.tours.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TypeService implements ITypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        typeRepository.deleteById(id);
    }
}
