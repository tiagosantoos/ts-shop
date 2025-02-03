package com.shopping.ts_shop.service;

import com.shopping.ts_shop.model.Cliente;
import com.shopping.ts_shop.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getEmail().isEmpty() ? cliente.getNome() : clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail().isEmpty() ? cliente.getEmail()  : clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone().isEmpty() ? cliente.getTelefone() : clienteAtualizado.getTelefone());
            cliente.setEndereco(clienteAtualizado.getEndereco().isEmpty() ?  cliente.getEndereco() : clienteAtualizado.getEndereco());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

}
