package br.com.fiap.ecommerce.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class SaveProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;

    private String codigo;

    private int quantidade;
    private double valor;

    private LocalDate deletedAt;

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
