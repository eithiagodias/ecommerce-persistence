package br.com.fiap.ecommerce.service.interfaces;

import br.com.fiap.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ecommerce.dto.SaveProdutoDTO;

import java.util.List;

public interface IProdutoService {
    List<ProdutoDTO> list();
    ProdutoDTO get(long id);
    ProdutoDTO create(SaveProdutoDTO produto);
    ProdutoDTO update(Long id, SaveProdutoDTO produto);
    void delete(long id);
}
