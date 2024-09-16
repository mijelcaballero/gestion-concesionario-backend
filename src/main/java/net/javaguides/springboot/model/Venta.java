package net.javaguides.springboot.model;

import java.time.LocalDate;

public class Venta {

    private Long id;
    private Long clienteId;
    private LocalDate fecha;
    private double total;
    
    
    public Venta() {
		super();
	}

	public Venta(Long clienteId, LocalDate fecha, double total) {
		super();
		this.clienteId = clienteId;
		this.fecha = fecha;
		this.total = total;
	}

	// Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
