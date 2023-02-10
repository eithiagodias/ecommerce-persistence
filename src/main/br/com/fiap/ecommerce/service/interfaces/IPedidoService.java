package br.com.fiap.ecommerce.service.interfaces;

import br.com.fiap.ecommerce.dto.PedidoDTO;
import br.com.fiap.ecommerce.dto.SavePedidoDTO;
import br.com.fiap.ecommerce.dto.UpdatePedidoDTO;
import br.com.fiap.ecommerce.entity.model.StatusPedido;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.util.List;

public interface IPedidoService {

    List<PedidoDTO> list();

    PedidoDTO create(SavePedidoDTO pedidoDto) throws Exception;

    PedidoDTO get(Long id);

    PedidoDTO update(Long id, UpdatePedidoDTO pedidoDTO) throws Exception;

    void delete(Long id);

}
