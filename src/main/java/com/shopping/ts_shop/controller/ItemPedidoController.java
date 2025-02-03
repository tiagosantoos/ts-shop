package com.shopping.ts_shop.controller;

import com.shopping.ts_shop.model.ItemPedido;
import com.shopping.ts_shop.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens-pedido")
@Validated
public class ItemPedidoController {
    @Autowired
    private  ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedido>> listarTodos() {
        return ResponseEntity.ok(itemPedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long id) {
        return itemPedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemPedido> criar(@Valid @RequestBody ItemPedido itemPedido) {
        return ResponseEntity.ok(itemPedidoService.criar(itemPedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @Valid @RequestBody ItemPedido itemPedido) {
        return ResponseEntity.ok(itemPedidoService.atualizar(id, itemPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        itemPedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
