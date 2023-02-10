package br.com.fiap.ecommerce.entity.model;

public enum StatusPedido{
    DELETADO,CRIADO,CANCELADO,AGUARDANDO_PAGAMENTO,PAGO,EMBALANDO,ENVIADO,TRANSITO,ENTREGUE;

    public static boolean contains(String test) {

        for (StatusPedido c : StatusPedido.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}