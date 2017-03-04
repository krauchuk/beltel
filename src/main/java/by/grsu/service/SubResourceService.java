package by.grsu.service;

import by.grsu.entity.SubResource;

import java.util.List;

public interface SubResourceService  {
    List<SubResource> getAll();
    SubResource getById(long id);
    void delete(long id);
    SubResource save(SubResource subResource);
    List<SubResource> getByResourceId(long id);
}
