package ma.zineb.projet.school.repositories;

import ma.zineb.projet.school.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.filiere.id = ?1")
    List<Student> findStudentsByFiliere(Long id);
}
