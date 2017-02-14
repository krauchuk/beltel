package by.grsu.service.Impl;

import by.grsu.entity.Resource;
import by.grsu.repository.ResourceRepository;
import by.grsu.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository service;

    public List<Resource> getAll() {
        return service.findAll();
    }

    public Resource getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Resource save(Resource resource) {
        return service.saveAndFlush(resource);
    }
}
