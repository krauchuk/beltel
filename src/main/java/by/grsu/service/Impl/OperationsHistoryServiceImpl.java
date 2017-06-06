package by.grsu.service.Impl;

import by.grsu.entity.OperationsHistory;
import by.grsu.repository.OperationsHistoryRepository;
import by.grsu.service.OperationsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationsHistoryServiceImpl implements OperationsHistoryService {

    @Autowired
    OperationsHistoryRepository service;

    public List<OperationsHistory> getAll() {
        return service.findAll();
    }

    public void delete(long id) {
        service.delete(id);
    }

    public OperationsHistory save(OperationsHistory operationsHistory) {
        return service.saveAndFlush(operationsHistory);
    }

    public OperationsHistory getById(long id) {
        return service.findOne(id);
    }
}
