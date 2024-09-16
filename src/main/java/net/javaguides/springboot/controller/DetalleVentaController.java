package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.DetalleVenta;
import net.javaguides.springboot.service.DetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PostMapping
    public ResponseEntity<String> crearDetalleVenta(@RequestParam Long ventaId, @RequestParam String categoria,
                                                    @RequestParam int itemId, @RequestParam int cantidad,
                                                    @RequestParam double precio) {
        detalleVentaService.crearDetalleVenta(ventaId, categoria, itemId, cantidad, precio);
        return new ResponseEntity<>("Detalle de venta creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping("/{ventaId}")
    public ResponseEntity<List<DetalleVenta>> obtenerDetallesPorVentaId(@PathVariable Long ventaId) {
        List<DetalleVenta> detalles = detalleVentaService.obtenerDetallesPorVentaId(ventaId);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }
}
