package uz.project.services;

import org.springframework.stereotype.Service;
import uz.project.models.Student;
import uz.project.repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student getStudent(Long id){
        return studentRepository.findStudentById(id);
    }

}
