package br.com.fiap.ecommerce.dto;

import br.com.fiap.ecommerce.entity.Cliente;
import br.com.fiap.ecommerce.entity.Endereco;
import br.com.fiap.ecommerce.entity.Pedido;
import br.com.fiap.ecommerce.entity.model.Sexo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ClienteDTO implements Serializable {

    private Long id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;

    private LocalDate deletedAt;
    private Sexo sexo;

    private String telefone;

    private List<Endereco> enderecos;
    private List<Pedido> pedidos;

    public ClienteDTO(Long id, String cpf, String nome, LocalDate dataNascimento, Sexo sexo, String telefone, List<Endereco> enderecos, List<Pedido> pedidos) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.enderecos = enderecos;
        this.pedidos = pedidos;
    }

    public ClienteDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente _toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setDataNascimento(dataNascimento);
        cliente.setDeletedAt(deletedAt);
        cliente.setSexo(sexo);
        cliente.setTelefone(telefone);
        cliente.setEnderecos((List<Endereco>)enderecos);

        return cliente;
    }
}
