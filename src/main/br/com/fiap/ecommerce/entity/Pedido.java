package br.com.fiap.ecommerce.entity;

import br.com.fiap.ecommerce.dto.PedidoDTO;
import br.com.fiap.ecommerce.entity.model.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "pedido", schema = "loja")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPedido = LocalDate.now();

    @Column(nullable = false)
    private StatusPedido statusPedido;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItensPedido> itensPedido;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public Pedido() {}

    public Pedido(PedidoDTO pedido) {
        this.dataPedido = pedido.getDataPedido();
        this.itensPedido = pedido.getItensPedido();
        this.cliente = pedido.getCliente();
        this.statusPedido = pedido.getStatusPedido();
    }

    public Pedido(Long id, LocalDate dataPedido, StatusPedido statusPedido, List<ItensPedido> itensPedido, Cliente cliente) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.itensPedido = itensPedido;
        this.cliente = cliente;
        this.statusPedido = statusPedido;
    }

    public PedidoDTO _toDto() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(id);
        pedidoDTO.setCliente(cliente);
        pedidoDTO.setItensPedido(itensPedido);
        pedidoDTO.setDataPedido(dataPedido);
        pedidoDTO.setStatusPedido(statusPedido);
        return pedidoDTO;
    }

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
}
