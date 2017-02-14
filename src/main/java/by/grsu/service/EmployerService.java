package by.grsu.service;

import by.grsu.entity.Employer;

import java.util.List;

public interface EmployerService {
    List<Employer> getAll();
    Employer getById(long id);
    void delete(long id);
    Employer save(Employer employer);
}
