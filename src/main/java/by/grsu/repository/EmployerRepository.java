package by.grsu.repository;

import by.grsu.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    @Query("SELECT e FROM Employer e WHERE e.zues_id.id = :id")
    List<Employer> getByZuesId(@Param("id") long id);

    @Query("SELECT e FROM Employer e WHERE e.post_id.id = :id")
    List<Employer> getByPostId(@Param("id") long id);

    @Query("SELECT e FROM Employer e WHERE e.sector_id.id = :id")
    List<Employer> getBySectorId(@Param("id") long id);

    @Query("SELECT e FROM Employer e WHERE e.division_id.id = :id")
    List<Employer> getByDivisionId(@Param("id") long id);
}
