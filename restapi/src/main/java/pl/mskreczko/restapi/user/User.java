package pl.mskreczko.restapi.user;

import lombok.NoArgsConstructor;
import pl.mskreczko.restapi.task.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class User {
    @Id
    @SequenceGenerator(name="user_id_seq", sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "fk_user")
    List<Task> tasks = new ArrayList<>();
}
