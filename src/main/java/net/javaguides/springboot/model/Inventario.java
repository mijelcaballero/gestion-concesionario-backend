package net.javaguides.springboot.model;

import java.sql.Timestamp;

public class Inventario {

    private Long id;
    private String categoria; // 'vehiculo', 'motocicleta', 'pesado'
    private int itemId;
    private int cantidad;
    private Timestamp fechaActualizacion;
    
    
    public Inventario() {
		
	}

	public Inventario(String categoria, int itemId, int cantidad) {
		super();
		this.categoria = categoria;
		this.itemId = itemId;
		this.cantidad = cantidad;
		this.fechaActualizacion = new Timestamp(System.currentTimeMillis());
	}

	// Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}

