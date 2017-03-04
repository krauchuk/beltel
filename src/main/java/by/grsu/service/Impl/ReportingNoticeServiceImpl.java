package by.grsu.service.Impl;

import by.grsu.entity.ReportingNotice;
import by.grsu.repository.ReportingNoticeRepository;
import by.grsu.service.ReportingNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingNoticeServiceImpl implements ReportingNoticeService{

    @Autowired
    ReportingNoticeRepository service;

    public List<ReportingNotice> getAll() {
        return service.findAll();
    }

    public ReportingNotice getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public ReportingNotice save(ReportingNotice reportingNotice) {
        return service.saveAndFlush(reportingNotice);
    }

    public List<ReportingNotice> getByEmployerId(long id) {
        return service.getByEmployerId(id);
    }

    public List<ReportingNotice> getByStatus(boolean status) {
        return service.getByStatus(status);
    }
}
