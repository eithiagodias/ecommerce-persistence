package br.com.fiap.ecommerce.repository;

import br.com.fiap.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p where p.statusPedido > 0")
    List<Pedido> getAllPedidos();
}
