package ma.zineb.projet.school.services;

import ma.zineb.projet.school.dao.IDao;
import ma.zineb.projet.school.entities.Role;
import ma.zineb.projet.school.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IDao<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(Role role) {
        try {
            roleRepository.delete(role);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

}
