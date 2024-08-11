package com.example.salesexam.service;

import com.example.salesexam.model.Salesman;
import com.example.salesexam.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesmanService {

    @Autowired
    private SalesmanRepository repository;

    public List<Salesman> listAll() {
        return repository.findAll();
    }

    public Salesman save(Salesman salesman) {
        // If the ID is not null, we are updating an existing record
        if (salesman.getId() != null) {
            Salesman existingSalesman = repository.findById(salesman.getId()).orElse(null);
            if (existingSalesman != null) {
                existingSalesman.setName(salesman.getName());
                existingSalesman.setAmount(salesman.getAmount());
                existingSalesman.setDot(salesman.getDot());
                existingSalesman.setItem(salesman.getItem());
                return repository.save(existingSalesman);
            }
        }
        // If the ID is null, this is a new entry
        return repository.save(salesman);
    }


    public Salesman get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
