package dev.kropotov;


import dev.kropotov.service.LoginImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EntityScan("dev.kropotov.entity")
@ComponentScan("dev.kropotov.*")
@EnableJpaRepositories(basePackages = "dev.kropotov.repository")
@EnableTransactionManagement
public class SpringJpaApplication {
    private static LoginImportService loginImportService;

    @Autowired
    public SpringJpaApplication(LoginImportService loginImportService) {
        this.loginImportService = loginImportService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
        loginImportService.load();
    }
}
