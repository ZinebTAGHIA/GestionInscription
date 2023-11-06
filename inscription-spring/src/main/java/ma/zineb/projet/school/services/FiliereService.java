package ma.zineb.projet.school.services;

import ma.zineb.projet.school.dao.IDao;
import ma.zineb.projet.school.entities.Filiere;
import ma.zineb.projet.school.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService implements IDao<Filiere> {

    @Autowired
    private FiliereRepository filiereRepository;

    @Override
    public Filiere create(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    @Override
    public boolean delete(Filiere filiere) {
        try {
            filiereRepository.delete(filiere);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Filiere update(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    @Override
    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    @Override
    public Filiere findById(Long id) {
        return filiereRepository.findById(id).orElse(null);
    }

}
