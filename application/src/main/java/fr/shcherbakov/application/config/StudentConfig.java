package fr.shcherbakov.application.config;

import fr.shcherbakov.application.models.Student;
import fr.shcherbakov.application.repos.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLiner(StudentRepository repository) {
        return args -> {

        List<Student> listOfStudents = List.of(
                new Student("John Doll", "john.doll@yahoo.fr", LocalDate.of(2000, JANUARY, 16)),
                new Student("Jane Pippa", "jane.pippa@yahoo.fr", LocalDate.of(2001, JANUARY, 16))
        );
        repository.saveAll(listOfStudents);
        };
    };
}
