package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Venta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class VentaRepository {

    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Venta> rowMapper = new RowMapper<Venta>() {
        @Override
        public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
            Venta venta = new Venta();
            venta.setId(rs.getLong("id"));
            venta.setClienteId(rs.getLong("cliente_id"));
            venta.setFecha(rs.getObject("fecha", LocalDate.class));
            venta.setTotal(rs.getDouble("total"));
            return venta;
        }
    };

    // Guardar una nueva venta
    public int save(Venta venta) {
        String sql = "INSERT INTO ventas (cliente_id, fecha, total) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, venta.getClienteId(), venta.getFecha(), venta.getTotal());
    }

    // Obtener venta por ID
    public Venta findById(Long id) {
        String sql = "SELECT * FROM ventas WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Obtener todas las ventas
    public List<Venta> findAll() {
        String sql = "SELECT * FROM ventas";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
