package br.com.fiap.ecommerce.service.interfaces;

import br.com.fiap.ecommerce.dto.EnderecoDTO;
import java.util.List;

public interface IEnderecoService {
    List<EnderecoDTO> list();
    EnderecoDTO get(long id);
    EnderecoDTO create(EnderecoDTO endereco);
    EnderecoDTO update(Long id, EnderecoDTO endereco);
    void delete(long id);
}
