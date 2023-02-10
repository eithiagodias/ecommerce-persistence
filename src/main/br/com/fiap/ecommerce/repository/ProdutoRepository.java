package br.com.fiap.ecommerce.repository;

import br.com.fiap.ecommerce.entity.Cliente;
import br.com.fiap.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where p.deletedAt = null")
    List<Produto> getAllProdutos();
}
