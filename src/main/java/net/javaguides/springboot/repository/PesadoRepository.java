package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Pesado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PesadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear pesado
    public void save(Pesado pesado) {
        String sql = "INSERT INTO pesados (name, image, description, category, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pesado.getName(), pesado.getImage(), pesado.getDescription(), pesado.getCategory(), pesado.getPrice());
    }

    // Buscar pesado por ID
    public Pesado findById(Long id) {
        String sql = "SELECT * FROM pesados WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PesadoRowMapper());
    }

    // Listar todos los pesados
    public List<Pesado> findAll() {
        String sql = "SELECT * FROM pesados";
        return jdbcTemplate.query(sql, new PesadoRowMapper());
    }

    // Actualizar pesado
    public void update(Pesado pesado) {
        String sql = "UPDATE pesados SET name = ?, image = ?, description = ?, category = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, pesado.getName(), pesado.getImage(), pesado.getDescription(), pesado.getCategory(), pesado.getPrice(), pesado.getId());
    }

    // Eliminar pesado
    public void delete(Long id) {
        String sql = "DELETE FROM pesados WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper para mapear el resultado de la consulta
    public static class PesadoRowMapper implements RowMapper<Pesado> {
        @Override
        public Pesado mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pesado pesado = new Pesado();
            pesado.setId(rs.getLong("id"));
            pesado.setName(rs.getString("name"));
            pesado.setImage(rs.getString("image"));
            pesado.setDescription(rs.getString("description"));
            pesado.setCategory(rs.getString("category"));
            pesado.setPrice(rs.getDouble("price"));
            return pesado;
        }
    }
}
