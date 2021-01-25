package fr.shcherbakov.application.services;

import fr.shcherbakov.application.models.Student;
import fr.shcherbakov.application.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();

//                List.of(
//                new Student(1L,"John Doll", "john.doll@yahoo.fr", LocalDate.of(2000, Month.JANUARY, 16), 21 ),
//                new Student(2L,"Jane Pippa", "jane.pippa@yahoo.fr", LocalDate.of(2001, Month.JANUARY, 16), 20)
//        );
    }

    public void addStudent(Student student) {
        Optional<Student> email = studentRepository.findByEmail(student.getEmail());
        if (email.isEmpty()) {
            studentRepository.save(student);
        }
        else throw new IllegalStateException("this email already exists");
    }

    public void deleteStudent(Long studentID) {
        if (studentRepository.existsById(studentID)) {
            studentRepository.deleteById(studentID);
        }
        else throw new IllegalStateException("the student with id " + studentID + " does not exist");
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email) {

        if (studentRepository.existsById(studentID)) {
            Student student = studentRepository.findById(studentID).get();
            if (name !=null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
                student.setName(name);
            }
            if (email !=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
                student.setEmail(email);
            }

        }
        else throw  new IllegalStateException("the student with id " + studentID + " does not exist");
    }
}
