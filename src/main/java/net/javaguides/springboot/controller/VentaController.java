package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Venta;
import net.javaguides.springboot.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<String> crearVenta(@RequestParam Long clienteId, @RequestParam double total) {
        ventaService.crearVenta(clienteId, total);
        return new ResponseEntity<>("Venta creada con éxito", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }
}
