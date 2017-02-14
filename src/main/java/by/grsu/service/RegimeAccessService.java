package by.grsu.service;

import by.grsu.entity.RegimeAccess;

import java.util.List;

public interface RegimeAccessService {
    List<RegimeAccess> getAll();
    RegimeAccess getById(long id);
    void delete(long id);
    RegimeAccess save(RegimeAccess regimeAccess);
}
