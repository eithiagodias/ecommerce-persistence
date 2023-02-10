package br.com.fiap.ecommerce.service;

import br.com.fiap.ecommerce.dto.PedidoDTO;
import br.com.fiap.ecommerce.dto.SaveItemPedidoDTO;
import br.com.fiap.ecommerce.dto.SavePedidoDTO;
import br.com.fiap.ecommerce.dto.UpdatePedidoDTO;
import br.com.fiap.ecommerce.entity.*;
import br.com.fiap.ecommerce.entity.model.StatusPedido;
import br.com.fiap.ecommerce.repository.ClienteRepository;
import br.com.fiap.ecommerce.repository.ItensPedidoRepository;
import br.com.fiap.ecommerce.repository.PedidoRepository;
import br.com.fiap.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.service.interfaces.IPedidoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService implements IPedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional
    @Cacheable(value = "allPedidosCache", unless = "#result.size() == 0")
    public List<PedidoDTO> list() {
        List<PedidoDTO> lista = new ArrayList<>();
        pedidoRepository.getAllPedidos().forEach(p -> lista.add(new PedidoDTO(p)));
        return lista;
    }

    @Override
    @Transactional
    @Cacheable(value = "pedidoCache", key = "#id")
    public PedidoDTO get(Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        return pedido._toDto();
    }

    @Override
    @Transactional
    @Caching(evict= {
            @CacheEvict(value= "allPedidosCache", allEntries= true),
            @CacheEvict(value= "allClientesCache", allEntries= true)
    })
    public PedidoDTO create(SavePedidoDTO pedidoDto) throws Exception {
        String cpf = pedidoDto.getCpf();
        Cliente cliente = clienteRepository.findClienteByCpf(cpf);
        if(cliente == null) {
            throw new Exception("Cliente com cpf={"+cpf+"} nao existe");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatusPedido(StatusPedido.CRIADO);
        pedidoRepository.saveAndFlush(pedido);

        List<ItensPedido> itens = new ArrayList<>();

        ItensPedido lista = new ItensPedido();
        for (int i = 0; i < pedidoDto.getItensPedido().size(); i++) {
            SaveItemPedidoDTO item = pedidoDto.getItensPedido().get(i);
            Long produtoId = item.getId();
            Produto produto = produtoRepository.findById(produtoId).get();
            if(produto == null) {
                throw new Exception("Produto com id={"+produtoId+"} nao existe");
            }
            lista.setNome(produto.getNome());
            lista.setQuantidade(item.getQuantidade());
            lista.setValor(item.getQuantidade() * produto.getValor());
            lista.setProduto(produto);
            lista.setPedido(pedido);
            itens.add(lista);
        }

        itensPedidoRepository.saveAllAndFlush(itens);

        pedido.setItensPedido(itens);
        return new PedidoDTO(pedido);
    }


    @Override
    @Caching(
            put= { @CachePut(value= "pedidoCache", key= "#id") },
            evict= { @CacheEvict(value= "allPedidosCache", allEntries= true) }
    )
    public PedidoDTO update(Long id, UpdatePedidoDTO pedidoDTO) throws Exception {
        String statusPedido = pedidoDTO.getStatusPedido();
        Pedido pedido = pedidoRepository.findById(id).get();

        if(pedido == null) {
            throw new Exception("Pedido com id={"+id+"} nao existe");
        }

        if(!StatusPedido.contains(statusPedido)) {
            throw new Exception("Status={"+statusPedido+"} nao e valido");
        }

        pedido.setId(id);
        pedido.setStatusPedido(StatusPedido.valueOf(statusPedido));
        Pedido pedidoUpdated = pedidoRepository.save(pedido);
        return pedidoUpdated._toDto();
    }

    @Caching(evict = { @CacheEvict(value = "pedidoCache", key = "#id"),
            @CacheEvict(value = "allPedidosCache", allEntries = true) })
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        pedido.setStatusPedido(StatusPedido.DELETADO);
        pedidoRepository.save(pedido);
    }
}
