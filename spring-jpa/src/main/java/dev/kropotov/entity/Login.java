package dev.kropotov.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "logins")
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String application;

    @Column
    private LocalDateTime accessDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
