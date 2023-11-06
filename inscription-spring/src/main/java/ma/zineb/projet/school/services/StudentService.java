package ma.zineb.projet.school.services;

import ma.zineb.projet.school.dao.IDao;
import ma.zineb.projet.school.entities.Student;
import ma.zineb.projet.school.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IDao<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean delete(Student student) {
        try {
            studentRepository.delete(student);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> findStudentsByFiliere(Long id) {
        return studentRepository.findStudentsByFiliere(id);
    }
}
