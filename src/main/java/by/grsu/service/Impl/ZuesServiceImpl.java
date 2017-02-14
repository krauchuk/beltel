package by.grsu.service.Impl;

import by.grsu.entity.Zues;
import by.grsu.repository.ZuesRepository;
import by.grsu.service.ZuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZuesServiceImpl implements ZuesService {

    @Autowired
    ZuesRepository service;

    public List<Zues> getAll() {
        return service.findAll();
    }

    public Zues getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Zues save(Zues zues) {
        return service.saveAndFlush(zues);
    }
}
