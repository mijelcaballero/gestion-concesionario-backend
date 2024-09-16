package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Pesado;
import net.javaguides.springboot.repository.PesadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesadoService {

    @Autowired
    private PesadoRepository pesadoRepository;

    // Crear Pesado
    public Pesado createPesado(Pesado pesado) {
        pesadoRepository.save(pesado);
        return pesado;
    }

    // Buscar Pesado por ID
    public Pesado getPesadoById(Long id) {
        return pesadoRepository.findById(id);
    }

    // Listar todos los Pesados
    public List<Pesado> getAllPesados() {
        return pesadoRepository.findAll();
    }

    // Actualizar Pesado
    public Pesado updatePesado(Long id, Pesado pesado) {
        Pesado existingPesado = pesadoRepository.findById(id);
        if (existingPesado != null) {
            existingPesado.setName(pesado.getName());
            existingPesado.setImage(pesado.getImage());
            existingPesado.setDescription(pesado.getDescription());
            existingPesado.setCategory(pesado.getCategory());
            existingPesado.setPrice(pesado.getPrice());
            pesadoRepository.update(existingPesado);
        }
        return existingPesado;
    }

    // Eliminar Pesado
    public void deletePesado(Long id) {
        pesadoRepository.delete(id);
    }
}
