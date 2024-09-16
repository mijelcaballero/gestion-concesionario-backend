package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Moto;
import net.javaguides.springboot.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    // Crear Moto
    public Moto createMoto(Moto moto) {
        motoRepository.save(moto);
        return moto;
    }

    // Buscar Moto por ID
    public Moto getMotoById(Long id) {
        return motoRepository.findById(id);
    }

    // Listar todas las Motos
    public List<Moto> getAllMotos() {
        return motoRepository.findAll();
    }

    // Actualizar Moto
    public Moto updateMoto(Long id, Moto moto) {
        Moto existingMoto = motoRepository.findById(id);
        if (existingMoto != null) {
            existingMoto.setName(moto.getName());
            existingMoto.setImage(moto.getImage());
            existingMoto.setDescription(moto.getDescription());
            existingMoto.setCategory(moto.getCategory());
            existingMoto.setPrice(moto.getPrice());
            motoRepository.update(existingMoto);
        }
        return existingMoto;
    }

    // Eliminar Moto
    public void deleteMoto(Long id) {
        motoRepository.delete(id);
    }
}
