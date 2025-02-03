package com.shopping.ts_shop.service;

import com.shopping.ts_shop.model.Cliente;
import com.shopping.ts_shop.model.ItemPedido;
import com.shopping.ts_shop.model.Pedido;
import com.shopping.ts_shop.repository.ClienteRepository;
import com.shopping.ts_shop.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }


    @Transactional
    public Pedido criar( Pedido pedido) {
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        pedido.setTotal(calcularTotal(pedido));
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Long id,  Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setStatus(pedidoAtualizado.getStatus());
            pedido.setTotal(calcularTotal(pedido));
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }

    private BigDecimal calcularTotal(Pedido pedido) {
        return pedido.getItens().stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
