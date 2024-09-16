package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Auto;
import net.javaguides.springboot.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    // Crear Auto
    public Auto createAuto(Auto auto) {
    	autoRepository.save(auto);
        return auto;
    }

    // Buscar Auto por ID
    public Auto getAutoById(Long id) {
        return autoRepository.findById(id);
    }

    // Listar todos los Autos
    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    // Actualizar Auto
    public Auto updateAuto(Long id, Auto auto) {
    	Auto existingAuto = autoRepository.findById(id);
        if (existingAuto != null) {
        	existingAuto.setName(auto.getName());
        	existingAuto.setImage(auto.getImage());
        	existingAuto.setDescription(auto.getDescription());
        	existingAuto.setCategory(auto.getCategory());
        	existingAuto.setPrice(auto.getPrice());
        	autoRepository.update(existingAuto);
        }
        return existingAuto;
    }

    // Eliminar Auto
    public void deleteAuto(Long id) {
    	autoRepository.delete(id);
    }
}
