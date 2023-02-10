package br.com.fiap.ecommerce.dto;

import java.io.Serializable;

public class SaveItemPedidoDTO implements Serializable {
    private Long id;
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public SaveItemPedidoDTO(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }
}
