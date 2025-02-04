package com.shopping.ts_shop.controller;

import com.shopping.ts_shop.model.Pedido;
import com.shopping.ts_shop.model.Produto;
import com.shopping.ts_shop.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Validated
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.criar(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.atualizar(id, pedido));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(pedidoService.count());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pedido>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(pedidoService.buscarPorNome(nome));
    }
}
