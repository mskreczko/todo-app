package pl.mskreczko.restapi.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT t from Task t WHERE t.user.email = ?1")
    List<Task> findByUsername(String username);

    @Query(value = "SELECT t from Task t WHERE t.user.email = ?2 AND t.id = ?1")
    Task findByIdAndUsername(Integer id, String username);
}
