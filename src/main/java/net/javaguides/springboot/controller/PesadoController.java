package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Pesado;
import net.javaguides.springboot.service.PesadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PesadoController {

    @Autowired
    private PesadoService pesadoService;

    public PesadoController(PesadoService pesadoService) {
        this.pesadoService = pesadoService;
    }

    // Crear un nuevo Pesado
    @PostMapping("/pesados")
    public ResponseEntity<Pesado> createPesado(@RequestBody Pesado pesado) {
        Pesado nuevoPesado = pesadoService.createPesado(pesado);
        return new ResponseEntity<>(nuevoPesado, HttpStatus.CREATED);
    }

    // Obtener un Pesado por ID
    @GetMapping("/pesados/{id}")
    public ResponseEntity<Pesado> getPesadoById(@PathVariable Long id) {
        Pesado pesado = pesadoService.getPesadoById(id);
        return ResponseEntity.ok(pesado);
    }

    // Obtener todos los Pesados
    @GetMapping("/pesados")
    public List<Pesado> getAllPesados() {
        return pesadoService.getAllPesados();
    }

    // Actualizar un Pesado
    @PutMapping("/pesados/{id}")
    public ResponseEntity<Pesado> updatePesado(@PathVariable Long id, @RequestBody Pesado pesado) {
        Pesado pesadoActualizado = pesadoService.updatePesado(id, pesado);
        return ResponseEntity.ok(pesadoActualizado);
    }

    // Eliminar un Pesado
    @DeleteMapping("/pesados/{id}")
    public ResponseEntity<Void> deletePesado(@PathVariable Long id) {
        pesadoService.deletePesado(id);
        return ResponseEntity.noContent().build();
    }
}
