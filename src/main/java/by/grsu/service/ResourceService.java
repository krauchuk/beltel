package by.grsu.service;

import by.grsu.entity.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> getAll();
    Resource getById(long id);
    void delete(long id);
    Resource save(Resource resource);
}
