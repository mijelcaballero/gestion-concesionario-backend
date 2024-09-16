package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MotoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear moto
    public void save(Moto moto) {
        String sql = "INSERT INTO motos (name, image, description, category, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, moto.getName(), moto.getImage(), moto.getDescription(), moto.getCategory(), moto.getPrice());
    }

    // Buscar moto por ID
    public Moto findById(Long id) {
        String sql = "SELECT * FROM motos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MotoRowMapper());
    }

    // Listar todas las motos
    public List<Moto> findAll() {
        String sql = "SELECT * FROM motos";
        return jdbcTemplate.query(sql, new MotoRowMapper());
    }

    // Actualizar moto
    public void update(Moto moto) {
        String sql = "UPDATE motos SET name = ?, image = ?, description = ?, category = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, moto.getName(), moto.getImage(), moto.getDescription(), moto.getCategory(), moto.getPrice(), moto.getId());
    }

    // Eliminar moto
    public void delete(Long id) {
        String sql = "DELETE FROM motos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper para mapear el resultado de la consulta
    public static class MotoRowMapper implements RowMapper<Moto> {
        @Override
        public Moto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Moto moto = new Moto();
            moto.setId(rs.getLong("id"));
            moto.setName(rs.getString("name"));
            moto.setImage(rs.getString("image"));
            moto.setDescription(rs.getString("description"));
            moto.setCategory(rs.getString("category"));
            moto.setPrice(rs.getDouble("price"));
            return moto;
        }
    }
}
