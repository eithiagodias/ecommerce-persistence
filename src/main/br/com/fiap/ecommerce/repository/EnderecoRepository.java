package br.com.fiap.ecommerce.repository;

import br.com.fiap.ecommerce.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {}
