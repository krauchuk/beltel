package by.grsu.service.Impl;

import by.grsu.entity.SubResource;
import by.grsu.repository.SubResourceRepository;
import by.grsu.service.SubResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubResourceServiceImp implements SubResourceService {

    @Autowired
    SubResourceRepository service;

    public List<SubResource> getAll() {
        return service.findAll();
    }

    public SubResource getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public SubResource save(SubResource subResource) {
        return service.saveAndFlush(subResource);
    }

    public List<SubResource> getByResourceId(long id) {
        return service.getByResourceId(id);
    }
}
