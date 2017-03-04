package by.grsu.service.Impl;

import by.grsu.entity.Sector;
import by.grsu.repository.SectorRepository;
import by.grsu.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    SectorRepository service;

    public List<Sector> getAll() {
        return service.findAll();
    }

    public Sector getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Sector save(Sector sector) {
        return service.saveAndFlush(sector);
    }

    public List<Sector> getByDivisionId(long id) {
        return service.getByDivisionId(id);
    }
}
