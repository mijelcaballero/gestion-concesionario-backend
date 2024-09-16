package net.javaguides.springboot.model;

public class DetalleVenta {

    private Long id;
    private Long ventaId;
    private String categoria; // 'vehiculo', 'motocicleta', 'pesado'
    private int itemId;
    private int cantidad;
    private double precio;
    
    

    public DetalleVenta() {
		super();
	}

	public DetalleVenta(Long ventaId, String categoria, int itemId, int cantidad, double precio) {
		super();
		this.ventaId = ventaId;
		this.categoria = categoria;
		this.itemId = itemId;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	// Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVentaId() {
        return ventaId;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
