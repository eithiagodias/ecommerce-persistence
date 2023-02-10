package br.com.fiap.ecommerce.service.interfaces;

import br.com.fiap.ecommerce.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {

    List<ClienteDTO> list();

    ClienteDTO create(ClienteDTO clienteDTO);

    ClienteDTO get(Long id);

    void update(Long id, ClienteDTO clienteDTO);

    void delete(Long id);

}
