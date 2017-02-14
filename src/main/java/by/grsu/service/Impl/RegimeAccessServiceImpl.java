package by.grsu.service.Impl;

import by.grsu.entity.RegimeAccess;
import by.grsu.repository.RegimeAccessRepository;
import by.grsu.service.RegimeAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegimeAccessServiceImpl implements RegimeAccessService {

    @Autowired
    RegimeAccessRepository service;

    public List<RegimeAccess> getAll() {
        return service.findAll();
    }

    public RegimeAccess getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public RegimeAccess save(RegimeAccess regimeAccess) {
        return service.saveAndFlush(regimeAccess);
    }
}
