package net.javaguides.springboot.controller;


import net.javaguides.springboot.model.Auto;
import net.javaguides.springboot.service.AutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AutoController {

    @Autowired
    private AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    // Crear un nuevo Auto
    @PostMapping("/autos")
    public ResponseEntity<Auto> createAuto(@RequestBody Auto auto) {
    	Auto nuevoAuto = autoService.createAuto(auto);
        return new ResponseEntity<>(nuevoAuto, HttpStatus.CREATED);
    }

    // Obtener un Auto por ID
    @GetMapping("/autos/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable Long id) {
    	Auto producto = autoService.getAutoById(id);
        return ResponseEntity.ok(producto);
    }

    // Obtener todos los Autos
    @GetMapping("/autos")
    public List<Auto> getAllAutos() {
        return autoService.getAllAutos();
    }

    // Actualizar un Auto
    @PutMapping("/autos/{id}")
    public ResponseEntity<Auto> updateAuto(@PathVariable Long id, @RequestBody Auto auto) {
    	Auto autoActualizado = autoService.updateAuto(id, auto);
        return ResponseEntity.ok(autoActualizado);
    }

    // Eliminar un Auto
    @DeleteMapping("/autos/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
    	autoService.deleteAuto(id);
        return ResponseEntity.noContent().build();
    }
}
