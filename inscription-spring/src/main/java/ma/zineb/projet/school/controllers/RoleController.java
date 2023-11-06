package ma.zineb.projet.school.controllers;

import ma.zineb.projet.school.entities.Role;
import ma.zineb.projet.school.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Role roleDetails) {
        Role role = roleService.create(roleDetails);
        if (role == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Role role = roleService.findById(id);
        if (role == null) {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = roleService.delete(role);
            if (deleted) {
                return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Role associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Role role) {
        if (roleService.findById(id) == null) {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
        } else {
            role.setId(id);
            return new ResponseEntity<>(roleService.update(role), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Role>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Role role = roleService.findById(id);
        if (role == null) {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
    }
}
