package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.DetalleVenta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetalleVentaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DetalleVentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<DetalleVenta> rowMapper = new RowMapper<DetalleVenta>() {
        @Override
        public DetalleVenta mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setId(rs.getLong("id"));
            detalleVenta.setVentaId(rs.getLong("venta_id"));
            detalleVenta.setCategoria(rs.getString("categoria"));
            detalleVenta.setItemId(rs.getInt("item_id"));
            detalleVenta.setCantidad(rs.getInt("cantidad"));
            detalleVenta.setPrecio(rs.getDouble("precio"));
            return detalleVenta;
        }
    };

    // Guardar un nuevo detalle de venta
    public int save(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_ventas (venta_id, categoria, item_id, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, detalleVenta.getVentaId(), detalleVenta.getCategoria(),
                detalleVenta.getItemId(), detalleVenta.getCantidad(), detalleVenta.getPrecio());
    }

    // Obtener detalles de venta por el ID de la venta
    public List<DetalleVenta> findByVentaId(Long ventaId) {
        String sql = "SELECT * FROM detalle_ventas WHERE venta_id = ?";
        return jdbcTemplate.query(sql, rowMapper, ventaId);
    }
}
