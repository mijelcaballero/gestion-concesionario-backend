package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Cliente;
import net.javaguides.springboot.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository clienteRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Cliente createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setEmail(cliente.getEmail());
            existingCliente.setPassword(cliente.getPassword());
            existingCliente.setDireccion(cliente.getDireccion());
            existingCliente.setTelefono(cliente.getTelefono());
            clienteRepository.update(existingCliente);
        }
        return existingCliente;
    }

    public void deleteCliente(Long id) {
        clienteRepository.delete(id);
    }
    
    // Verificar credenciales
    public boolean validateCredentials(String email, String password) {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            return passwordEncoder.matches(password, cliente.getPassword());
        }
        return false;
    }
}
