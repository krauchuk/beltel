package by.grsu.repository;

import by.grsu.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DivisionRepository extends JpaRepository<Division, Long> {
}
