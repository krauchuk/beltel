package by.grsu.service;

import by.grsu.entity.Sector;

import java.util.List;

public interface SectorService {
    List<Sector> getAll();
    Sector getById(long id);
    void delete(long id);
    Sector save(Sector sector);
}
