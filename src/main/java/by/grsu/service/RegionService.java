package by.grsu.service;

import by.grsu.entity.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAll();
    Region getById(long id);
    void delete(long id);
    Region save(Region region);
}
