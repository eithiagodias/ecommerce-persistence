package br.com.fiap.ecommerce.service;

import br.com.fiap.ecommerce.dto.ClienteDTO;
import br.com.fiap.ecommerce.entity.Cliente;
import br.com.fiap.ecommerce.entity.Endereco;

import br.com.fiap.ecommerce.entity.Pedido;
import br.com.fiap.ecommerce.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.fiap.ecommerce.repository.ClienteRepository;
import br.com.fiap.ecommerce.repository.EnderecoRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	@Cacheable(value = "clienteCache", key = "#id")
	@Transactional
	public ClienteDTO get(Long id) {
		Cliente cliente = clienteRepository.getById(id);
		return cliente._toDto();
	}

	@Override
	@Cacheable(value = "allClientesCache", unless = "#result.size() == 0")
	@Transactional
	public List<ClienteDTO> list() {
		List<Cliente> listClientes = clienteRepository.getAllClientes();
		List<ClienteDTO> clientesList = new ArrayList<>();
		for (Cliente cliente : listClientes) {
			clientesList.add(cliente._toDto());
		}
		return clientesList;
	}

	@Override
	@Caching(evict= { @CacheEvict(value= "allClientesCache", allEntries= true) })
	public ClienteDTO create(ClienteDTO clienteDTO) {
		List<Endereco> enderecosList = clienteDTO.getEnderecos();
		Cliente cliente = clienteDTO._toEntity();
		if(enderecosList != null) {
			enderecosList.forEach(e -> e.setCliente(cliente));
			enderecoRepository.saveAll(enderecosList);
		}
		clienteRepository.save(cliente);
		return cliente._toDto();
	}

	@Override
	@Caching(
			put= { @CachePut(value= "clienteCache", key= "#id") },
			evict= { @CacheEvict(value= "allClientesCache", allEntries= true) }
	)
	public void update(Long id, ClienteDTO clienteDTO) {
		Cliente c = clienteRepository.findById(id).get();
		c.setNome(clienteDTO.getNome());
		c.setDataNascimento(clienteDTO.getDataNascimento());
		c.setTelefone(clienteDTO.getTelefone());
		c.setSexo(clienteDTO.getSexo());
		c.setEnderecos((List<Endereco>) clienteDTO.getEnderecos());
		c.setPedidos((List<Pedido>) clienteDTO.getPedidos());

		List<Endereco> enderecos;
		enderecos = c.getEnderecos();
		enderecos.forEach(e -> e.setCliente(c));

		enderecoRepository.saveAll(enderecos);
		clienteRepository.save(c);
	}

	@Caching(evict = { @CacheEvict(value = "clienteCache", key = "#id"),
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public void delete(Long id) {
		Cliente cliente = clienteRepository.getById(id);
		cliente.setDeletedAt(LocalDate.now());
		clienteRepository.save(cliente);
	}

}
