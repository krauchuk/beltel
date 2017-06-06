package by.grsu.controller;

import by.grsu.entity.OperationsHistory;
import by.grsu.service.Impl.OperationsHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class OperationsHistoryController {

    @Autowired
    private OperationsHistoryServiceImpl operationsHistoryService;

    public void saveOperation(String operation){
        OperationsHistory operationsHistory = new OperationsHistory();
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        operationsHistory.setDate(date);
        operationsHistory.setUserName(username);
        operationsHistory.setOperation(operation);
        operationsHistoryService.save(operationsHistory);
    }
}
