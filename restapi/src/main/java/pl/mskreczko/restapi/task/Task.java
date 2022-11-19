package pl.mskreczko.restapi.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mskreczko.restapi.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_sequence", allocationSize = 150)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    Task(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.user = user;
    }
}
