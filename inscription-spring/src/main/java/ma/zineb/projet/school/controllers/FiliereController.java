package ma.zineb.projet.school.controllers;

import ma.zineb.projet.school.entities.Filiere;
import ma.zineb.projet.school.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/filieres")
public class FiliereController {
    @Autowired
    private FiliereService filiereService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Filiere filiereDetails) {
        Filiere filiere = filiereService.create(filiereDetails);
        if (filiere == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(filiere, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Filiere filiere = filiereService.findById(id);
        if (filiere == null) {
            return new ResponseEntity<>("Filiere not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted =  filiereService.delete(filiere);
            if (deleted) {
                return new ResponseEntity<>("Filiere deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Filiere associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Filiere filiere) {
        if (filiereService.findById(id) == null) {
            return new ResponseEntity<>("Filiere not found", HttpStatus.NOT_FOUND);
        } else {
            filiere.setId(id);
            return new ResponseEntity<>(filiereService.update(filiere), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Filiere>> findAll() {
        return new ResponseEntity<>(filiereService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Filiere filiere = filiereService.findById(id);
        if (filiere == null) {
            return new ResponseEntity<>("Filiere not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(filiere, HttpStatus.OK);
        }
    }
}
