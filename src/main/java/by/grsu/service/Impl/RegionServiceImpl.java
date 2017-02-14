package by.grsu.service.Impl;

import by.grsu.entity.Region;
import by.grsu.repository.RegionRepository;
import by.grsu.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionRepository service;

    public List<Region> getAll() {
        return service.findAll();
    }

    public Region getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Region save(Region region) {
        return service.saveAndFlush(region);
    }
}
