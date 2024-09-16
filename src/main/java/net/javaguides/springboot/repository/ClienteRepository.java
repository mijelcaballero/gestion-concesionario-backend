package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper para mapear el resultado de la consulta a la clase Cliente
    private RowMapper<Cliente> rowMapper = (rs, rowNum) -> new Cliente(
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("direccion"),
            rs.getString("telefono")
            
    );

    // Crear un nuevo cliente
    public int save(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email, password, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, cliente.getNombre(), cliente.getEmail(),cliente.getPassword(), cliente.getDireccion(), cliente.getTelefono());
    }

    // Buscar un cliente por ID
    public Cliente findById(Long id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Buscar un cliente por email
    public Optional<Cliente> findByEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        try {
            Cliente cliente = jdbcTemplate.queryForObject(sql, rowMapper, email);
            return Optional.of(cliente);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Obtener todos los clientes
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM clientes";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Actualizar un cliente
    public int update(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, password = ? , direccion = ?, telefono = ? WHERE id = ?";
        return jdbcTemplate.update(sql, cliente.getNombre(), cliente.getEmail(), cliente.getPassword(), cliente.getDireccion(), cliente.getTelefono(), cliente.getId());
    }

    // Eliminar un cliente
    public int delete(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
