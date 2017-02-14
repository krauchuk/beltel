package by.grsu.service;

import by.grsu.entity.Division;

import java.util.List;

public interface DivisionService{
    List<Division> getAll();
    Division getById(long id);
    void delete(long id);
    Division save(Division division);
}
