package pl.mskreczko.restapi.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(Integer userId);

    @Query(value = "SELECT t from Task t WHERE t.user.id = ?1 and t.id = ?2")
    Task findByUserIdAndTaskId(Integer userId, Integer taskId);
}
