package by.grsu.service;

import by.grsu.entity.Employer;

import java.util.List;

public interface EmployerService {
    List<Employer> getAll();
    Employer getById(long id);
    void delete(long id);
    Employer save(Employer employer);
    List<Employer> getByZuesId(long id);
    List<Employer> getByPostId(long id);
    List<Employer> getBySectorId(long id);
    List<Employer> getByDivisionId(long id);
}
