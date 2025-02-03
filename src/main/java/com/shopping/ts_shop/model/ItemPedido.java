package com.shopping.ts_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O pedido é obrigatório")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @NotNull(message = "O produto é obrigatório")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull(message = "O preço unitário é obrigatório")
    @DecimalMin(value = "0.01", message = "O precoUnitario deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @NotNull(message = "O subtotal não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O subtotal deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal subtotal;

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}