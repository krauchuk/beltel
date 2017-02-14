package by.grsu.service;

import by.grsu.entity.ReportingNotice;

import java.util.List;

public interface ReportingNoticeService {
    List<ReportingNotice> getAll();
    ReportingNotice getById(long id);
    void delete(long id);
    ReportingNotice save(ReportingNotice reportingNotice);
    List<ReportingNotice> getByEmployerId(long id);
}
