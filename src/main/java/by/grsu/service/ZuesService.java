package by.grsu.service;

import by.grsu.entity.Zues;

import java.util.List;

public interface ZuesService {
    List<Zues> getAll();
    Zues getById(long id);
    void delete(long id);
    Zues save(Zues zues);
}
