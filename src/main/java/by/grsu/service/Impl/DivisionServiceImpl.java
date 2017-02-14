package by.grsu.service.Impl;

import by.grsu.entity.Division;
import by.grsu.repository.DivisionRepository;
import by.grsu.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    DivisionRepository service;

    public List<Division> getAll() {
        return service.findAll();
    }

    public Division getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Division save(Division division) {
        return service.saveAndFlush(division);
    }
}
