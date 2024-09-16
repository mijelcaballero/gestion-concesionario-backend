package net.javaguides.springboot.controller;

import net.javaguides.springboot.service.InventarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/concesionario")
@CrossOrigin(origins = "http://localhost:4200")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // Verificar stock disponible antes de realizar una venta
    @GetMapping("/inventario/verificar/{categoria}/{itemId}/{cantidad}")
    public ResponseEntity<Map<String, String>> verificarStock(
        @PathVariable String categoria,
        @PathVariable int itemId,
        @PathVariable int cantidad
    ) {
        Long inventarioId = inventarioService.obtenerInventarioIdPorCategoriaYItem(categoria, itemId);
        boolean disponible = inventarioService.hayStock(inventarioId, cantidad);

        Map<String, String> response = new HashMap<>();

        if (disponible) {
            response.put("mensaje", "Stock disponible");
            return ResponseEntity.ok(response); // Retornamos un JSON
        } else {
            response.put("mensaje", "Stock insuficiente");
            return ResponseEntity.badRequest().body(response); // Retornamos un JSON
        }
    }

    // Actualizar stock después de una venta
    @PostMapping("/inventario/vender/{categoria}/{itemId}/{cantidad}")
    public ResponseEntity<String> venderProducto(
        @PathVariable String categoria,
        @PathVariable int itemId,
        @PathVariable int cantidad
    ) {
        Long inventarioId = inventarioService.obtenerInventarioIdPorCategoriaYItem(categoria, itemId);
        boolean disponible = inventarioService.hayStock(inventarioId, cantidad);

        if (disponible) {
            inventarioService.actualizarStock(inventarioId, cantidad);
            return ResponseEntity.ok("Venta registrada y stock actualizado");
        } else {
            return new ResponseEntity<>("No hay suficiente stock", HttpStatus.BAD_REQUEST);
        }
    }

    // Agregar stock de vehículos, motocicletas o pesados
    @PostMapping("/inventario/agregar/{categoria}/{itemId}/{cantidad}")
    public ResponseEntity<String> agregarStock(
        @PathVariable String categoria,
        @PathVariable int itemId,
        @PathVariable int cantidad
    ) {
        Long inventarioId = inventarioService.obtenerInventarioIdPorCategoriaYItem(categoria, itemId);
        inventarioService.agregarStock(inventarioId, cantidad);
        return new ResponseEntity<>("Stock actualizado correctamente", HttpStatus.OK);
    }
}
