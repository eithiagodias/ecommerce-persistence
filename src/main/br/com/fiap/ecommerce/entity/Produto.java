package br.com.fiap.ecommerce.entity;

import br.com.fiap.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ecommerce.dto.SaveProdutoDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produto", schema = "loja")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = true)
    private LocalDate deletedAt;

    @Column
    private int quantidade;
    @Column
    private double valor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
    private List<ItensPedido> itensPedidos;

    public Produto() {}

    public Produto(ProdutoDTO produto) {
        this.nome = produto.getNome();
        this.codigo = produto.getCodigo();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
    }

    public Produto(SaveProdutoDTO produto) {
        this.nome = produto.getNome();
        this.codigo = produto.getCodigo();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }
}
