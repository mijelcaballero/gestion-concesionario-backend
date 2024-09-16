package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Inventario;
import net.javaguides.springboot.repository.InventarioRepository;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Verificar si hay suficiente stock antes de una venta
    public boolean hayStock(Long inventarioId, int cantidadSolicitada) {
        Inventario inventario = inventarioRepository.findById(inventarioId);
        return inventario.getCantidad() >= cantidadSolicitada;
    }

    // Actualizar el stock después de una venta
    public void actualizarStock(Long inventarioId, int cantidadVendida) {
        Inventario inventario = inventarioRepository.findById(inventarioId);
        int nuevaCantidad = inventario.getCantidad() - cantidadVendida;
        inventario.setCantidad(nuevaCantidad);
        inventario.setFechaActualizacion(new java.sql.Timestamp(System.currentTimeMillis()));
        inventarioRepository.update(inventario);
    }

    // Agregar stock cuando se agrega nuevo producto al inventario
    public void agregarStock(Long inventarioId, int cantidadAgregada) {
        Inventario inventario = inventarioRepository.findById(inventarioId);
        int nuevaCantidad = inventario.getCantidad() + cantidadAgregada;
        inventario.setCantidad(nuevaCantidad);
        inventario.setFechaActualizacion(new java.sql.Timestamp(System.currentTimeMillis()));
        inventarioRepository.update(inventario);
    }

    // Nuevo método para obtener el inventario por categoría y itemId
    public Long obtenerInventarioIdPorCategoriaYItem(String categoria, int itemId) {
        Inventario inventario = inventarioRepository.findByCategoriaAndItemId(categoria, itemId);
        return inventario != null ? inventario.getId() : null;
    }
}
