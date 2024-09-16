package net.javaguides.springboot.repository;



import net.javaguides.springboot.model.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear Auto
    public void save(Auto auto) {
        String sql = "INSERT INTO autos (name, image, description, category, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, auto.getName(), auto.getImage(), auto.getDescription(), auto.getCategory(), auto.getPrice());
    }

    // Buscar Auto por ID
    public Auto findById(Long id) {
        String sql = "SELECT * FROM autos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AutoRowMapper());
    }

    // Listar todos los Autos
    public List<Auto> findAll() {
        String sql = "SELECT * FROM autos";
        return jdbcTemplate.query(sql, new AutoRowMapper());
    }

    // Actualizar Autos
    public void update(Auto auto) {
        String sql = "UPDATE autos SET name = ?, image = ?, description = ?, category = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, auto.getName(), auto.getImage(), auto.getDescription(), auto.getCategory(), auto.getPrice(), auto.getId());
    }

    // Eliminar Autos
    public void delete(Long id) {
        String sql = "DELETE FROM autos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper para mapear el resultado de la consulta
    public static class AutoRowMapper implements RowMapper<Auto> {
        @Override
        public Auto mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Auto auto = new Auto();
        	auto.setId(rs.getLong("id"));
        	auto.setName(rs.getString("name"));
        	auto.setImage(rs.getString("image"));
        	auto.setDescription(rs.getString("description"));
        	auto.setCategory(rs.getString("category"));
        	auto.setPrice(rs.getDouble("price"));
            return auto;
        }
    }
}

