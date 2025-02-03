package com.shopping.ts_shop.service;

import com.shopping.ts_shop.model.ItemPedido;
import com.shopping.ts_shop.model.Pedido;
import com.shopping.ts_shop.model.Produto;
import com.shopping.ts_shop.repository.ItemPedidoRepository;
import com.shopping.ts_shop.repository.PedidoRepository;
import com.shopping.ts_shop.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ItemPedido> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    @Transactional
    public ItemPedido criar(ItemPedido itemPedido) {
        Pedido pedido = pedidoRepository.findById(itemPedido.getPedido().getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(itemPedido.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getEstoque() < itemPedido.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoUnitario(produto.getPreco());
        itemPedido.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade())));

        produto.setEstoque(produto.getEstoque() - itemPedido.getQuantidade());
        produtoRepository.save(produto);

        return itemPedidoRepository.save(itemPedido);
    }

    @Transactional
    public ItemPedido atualizar(Long id, ItemPedido itemPedidoAtualizado) {
        return itemPedidoRepository.findById(id).map(itemPedido -> {
            itemPedido.setQuantidade(itemPedidoAtualizado.getQuantidade());
            itemPedido.setSubtotal(itemPedido.getPrecoUnitario().multiply(
                    BigDecimal.valueOf(itemPedidoAtualizado.getQuantidade())));
            return itemPedidoRepository.save(itemPedido);
        }).orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
