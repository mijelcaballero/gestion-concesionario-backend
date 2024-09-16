package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Venta;
import net.javaguides.springboot.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    // Crear una nueva venta
    public void crearVenta(Long clienteId, double total) {
        Venta venta = new Venta(clienteId, LocalDate.now(), total);
        ventaRepository.save(venta);
    }

    // Obtener venta por ID
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    // Obtener todas las ventas
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }
}
