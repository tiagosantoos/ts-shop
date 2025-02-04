package com.shopping.ts_shop.repository;

import com.shopping.ts_shop.model.Cliente;
import com.shopping.ts_shop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteNomeContainingIgnoreCase(String nome);
}
