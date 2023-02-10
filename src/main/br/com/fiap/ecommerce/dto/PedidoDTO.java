package br.com.fiap.ecommerce.dto;

import br.com.fiap.ecommerce.entity.Cliente;
import br.com.fiap.ecommerce.entity.ItensPedido;
import br.com.fiap.ecommerce.entity.Pedido;
import br.com.fiap.ecommerce.entity.model.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate dataPedido;
    private List<ItensPedido> itensPedido;

    private StatusPedido statusPedido;

    @JsonIgnore
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItensPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItensPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public PedidoDTO() {

    }

    public Pedido _toEntity() {
        Pedido pedido = new Pedido();
        pedido.setId(this.getId());
        pedido.setDataPedido(this.getDataPedido());
        pedido.setItensPedido((List<ItensPedido>) this.getItensPedido());
        pedido.setCliente(this.getCliente());
        pedido.setStatusPedido(this.getStatusPedido());
        return pedido;
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.itensPedido = pedido.getItensPedido();
        this.cliente = pedido.getCliente();
        this.statusPedido = pedido.getStatusPedido();
    }
}
