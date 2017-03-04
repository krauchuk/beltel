package by.grsu.repository;

import by.grsu.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    @Query("SELECT s FROM Sector s WHERE s.division_id.id = :id")
    List<Sector> getByDivisionId(@Param("id") long id);
}
