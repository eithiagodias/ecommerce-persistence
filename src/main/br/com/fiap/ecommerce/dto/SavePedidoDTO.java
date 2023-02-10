package br.com.fiap.ecommerce.dto;

import java.util.List;

public class SavePedidoDTO {

    private String cpf;

    private List<SaveItemPedidoDTO> itensPedido;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<SaveItemPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<SaveItemPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public SavePedidoDTO(String cpf, List<SaveItemPedidoDTO> itensPedido) {
        this.cpf = cpf;
        this.itensPedido = itensPedido;
    }
}
