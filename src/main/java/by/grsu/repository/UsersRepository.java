package by.grsu.repository;

import by.grsu.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM Users u WHERE u.employer_id.id = :id")
    List<Users> getByEmployerId(@Param("id") long id);
}
