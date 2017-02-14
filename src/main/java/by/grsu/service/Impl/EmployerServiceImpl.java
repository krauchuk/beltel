package by.grsu.service.Impl;

import by.grsu.entity.Employer;
import by.grsu.repository.EmployerRepository;
import by.grsu.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    EmployerRepository service;

    public List<Employer> getAll() {
        return service.findAll();
    }

    public Employer getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Employer save(Employer employer) {
        return service.saveAndFlush(employer);
    }
}
