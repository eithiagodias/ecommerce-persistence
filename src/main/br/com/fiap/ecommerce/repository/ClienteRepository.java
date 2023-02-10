package br.com.fiap.ecommerce.repository;

import java.util.List;
import java.util.stream.Stream;

import br.com.fiap.ecommerce.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findClienteByCpf (String cpf);

    @Query("select c from Cliente c where c.deletedAt = null")
    List<Cliente> getAllClientes();
}
