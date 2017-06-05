package by.grsu.repository;

import by.grsu.entity.RepnoteRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepnoteResRepository extends JpaRepository<RepnoteRes, Long> {
    @Query("SELECT r FROM RepnoteRes r WHERE r.reportingNotice_id.id = :id")
    List<RepnoteRes> getByNoticeId(@Param("id") long id);

    @Query("SELECT r FROM RepnoteRes r WHERE r.subResource_id.id = :id")
    List<RepnoteRes> getBySubResourceId(@Param("id") long id);

    @Query("SELECT r FROM RepnoteRes r WHERE r.regimeAccess_id.id = :id")
    List<RepnoteRes> getByRegimeAccessId(@Param("id") long id);

    @Query("DELETE FROM RepnoteRes r WHERE r.reportingNotice_id.id = :id")
    @Modifying
    @Transactional
    void deleteByNoticeId(@Param("id") long id);

    @Query("DELETE FROM RepnoteRes r WHERE r.reportingNotice_id.id = :noticeId AND r.subResource_id.id = :subresId")
    @Modifying
    @Transactional
    void deleteByNoticeAndSubresId(@Param("noticeId") long noticeId, @Param("subresId") long subresId);
}
