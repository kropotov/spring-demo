package dev.kropotov.repository;

import dev.kropotov.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, Long> {
}
