package by.grsu.repository;

import by.grsu.entity.ReportingNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportingNoticeRepository extends JpaRepository<ReportingNotice, Long> {
    @Query("SELECT r FROM ReportingNotice r WHERE r.employers_id.id = :id")
    List<ReportingNotice> getByEmployerId(@Param("id") long id);

    @Query("SELECT r FROM ReportingNotice r WHERE r.status = :status")
    List<ReportingNotice> getByStatus(@Param("status") boolean status);
}
