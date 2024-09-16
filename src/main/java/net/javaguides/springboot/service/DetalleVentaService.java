package net.javaguides.springboot.service;

import net.javaguides.springboot.model.DetalleVenta;
import net.javaguides.springboot.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    // Crear un detalle de venta
    public void crearDetalleVenta(Long ventaId, String categoria, int itemId, int cantidad, double precio) {
        DetalleVenta detalleVenta = new DetalleVenta(ventaId, categoria, itemId, cantidad, precio);
        detalleVentaRepository.save(detalleVenta);
    }

    // Obtener detalles de una venta por ID
    public List<DetalleVenta> obtenerDetallesPorVentaId(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId);
    }
}
