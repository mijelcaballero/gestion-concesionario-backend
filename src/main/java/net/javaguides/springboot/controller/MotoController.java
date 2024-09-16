package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Moto;
import net.javaguides.springboot.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class MotoController {

    @Autowired
    private MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    // Crear una nueva Moto
    @PostMapping("/motos")
    public ResponseEntity<Moto> createMoto(@RequestBody Moto moto) {
        Moto nuevaMoto = motoService.createMoto(moto);
        return new ResponseEntity<>(nuevaMoto, HttpStatus.CREATED);
    }

    // Obtener una Moto por ID
    @GetMapping("/motos/{id}")
    public ResponseEntity<Moto> getMotoById(@PathVariable Long id) {
        Moto moto = motoService.getMotoById(id);
        return ResponseEntity.ok(moto);
    }

    // Obtener todas las Motos
    @GetMapping("/motos")
    public List<Moto> getAllMotos() {
        return motoService.getAllMotos();
    }

    // Actualizar una Moto
    @PutMapping("/motos/{id}")
    public ResponseEntity<Moto> updateMoto(@PathVariable Long id, @RequestBody Moto moto) {
        Moto motoActualizada = motoService.updateMoto(id, moto);
        return ResponseEntity.ok(motoActualizada);
    }

    // Eliminar una Moto
    @DeleteMapping("/motos/{id}")
    public ResponseEntity<Void> deleteMoto(@PathVariable Long id) {
        motoService.deleteMoto(id);
        return ResponseEntity.noContent().build();
    }
}
