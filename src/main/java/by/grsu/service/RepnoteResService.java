package by.grsu.service;

import by.grsu.entity.RepnoteRes;

import java.util.List;

public interface RepnoteResService {
    List<RepnoteRes> getAll();
    RepnoteRes getById(long id);
    void delete(long id);
    RepnoteRes save(RepnoteRes repnoteRes);
    List<RepnoteRes> getByNoticeId(long id);
    void deleteByNoticeId(long id);
    List<RepnoteRes> getBySubResourceId(long id);
    List<RepnoteRes> getByRegimeAccessId(long id);
}
