package ma.zineb.projet.school.controllers;

import ma.zineb.projet.school.entities.Student;
import ma.zineb.projet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Student studentDetails) {
        Student student = studentService.create(studentDetails);
        if (student == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            studentService.delete(student);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Student student) {
        if (studentService.findById(id) == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            student.setId(id);
            return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @GetMapping("/filiere/{id}")
    public ResponseEntity<List<Student>> getStudentsByFiliere(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findStudentsByFiliere(id), HttpStatus.OK);
    }
}
