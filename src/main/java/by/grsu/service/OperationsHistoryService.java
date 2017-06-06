package by.grsu.service;

import by.grsu.entity.OperationsHistory;

import java.util.List;

public interface OperationsHistoryService {
    List<OperationsHistory> getAll();
    void delete(long id);
    OperationsHistory save(OperationsHistory operationsHistory);
    OperationsHistory getById(long id);
}
