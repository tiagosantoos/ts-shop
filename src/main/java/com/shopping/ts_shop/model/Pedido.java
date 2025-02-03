package com.shopping.ts_shop.model;

import com.shopping.ts_shop.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O cliente é obrigatório")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    private LocalDateTime dataPedido = LocalDateTime.now();

    @NotNull(message = "O status do pedido é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.PENDENTE;

    @NotNull(message = "O total do pedido não pode ser nulo")
    @DecimalMin(value = "0.00", message = "O total não pode ser negativo")
    private BigDecimal total = BigDecimal.ZERO;

    @Size(min = 1, message = "O pedido deve ter pelo menos um item")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;
}
