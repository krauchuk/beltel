package by.grsu.service.Impl;

import by.grsu.entity.RepnoteRes;
import by.grsu.repository.RepnoteResRepository;
import by.grsu.service.RepnoteResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepnoteResServiceImpl implements RepnoteResService{

    @Autowired
    RepnoteResRepository service;

    public List<RepnoteRes> getAll() {
        return service.findAll();
    }

    public RepnoteRes getById(long id) {
        return service.findOne(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public RepnoteRes save(RepnoteRes repnoteRes) {
        return service.saveAndFlush(repnoteRes);
    }

    public List<RepnoteRes> getByNoticeId(long id) {
        return service.getByNoticeId(id);
    }

    public void deleteByNoticeId(long id) {
        service.deleteByNoticeId(id);
    }
}
