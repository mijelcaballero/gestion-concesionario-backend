package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Inventario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class InventarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public InventarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de ResultSet a objeto Inventario
    private RowMapper<Inventario> rowMapper = new RowMapper<Inventario>() {
        @Override
        public Inventario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inventario inventario = new Inventario();
            inventario.setId(rs.getLong("id"));
            inventario.setCategoria(rs.getString("categoria"));
            inventario.setItemId(rs.getInt("item_id"));
            inventario.setCantidad(rs.getInt("cantidad"));
            inventario.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
            return inventario;
        }
    };

    // Obtener inventario por ID
    public Inventario findById(Long id) {
        String sql = "SELECT * FROM inventario WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Obtener todos los inventarios
    public List<Inventario> findAll() {
        String sql = "SELECT * FROM inventario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Guardar nuevo inventario
    public int save(Inventario inventario) {
        String sql = "INSERT INTO inventario (categoria, item_id, cantidad, fecha_actualizacion) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            inventario.getCategoria(), 
            inventario.getItemId(), 
            inventario.getCantidad(),
            new Timestamp(System.currentTimeMillis()));
    }

    // Actualizar inventario existente
    public int update(Inventario inventario) {
        String sql = "UPDATE inventario SET categoria = ?, item_id = ?, cantidad = ?, fecha_actualizacion = ? WHERE id = ?";
        return jdbcTemplate.update(sql, 
            inventario.getCategoria(), 
            inventario.getItemId(), 
            inventario.getCantidad(), 
            new Timestamp(System.currentTimeMillis()), 
            inventario.getId());
    }

    // Eliminar inventario por ID
    public int deleteById(Long id) {
        String sql = "DELETE FROM inventario WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Nuevo método para buscar inventario por categoría y itemId
    public Inventario findByCategoriaAndItemId(String categoria, int itemId) {
        String sql = "SELECT * FROM inventario WHERE categoria = ? AND item_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, categoria, itemId);
    }
}
