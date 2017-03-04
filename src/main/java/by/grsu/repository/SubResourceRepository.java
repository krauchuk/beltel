package by.grsu.repository;

import by.grsu.entity.SubResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubResourceRepository extends JpaRepository<SubResource, Long> {
    @Query("SELECT sr FROM SubResource sr WHERE sr.resource_id.id = :id")
    List<SubResource> getByResourceId(@Param("id") long id);
}
