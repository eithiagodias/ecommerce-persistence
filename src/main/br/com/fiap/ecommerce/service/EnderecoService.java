package br.com.fiap.ecommerce.service;


import br.com.fiap.ecommerce.dto.EnderecoDTO;
import br.com.fiap.ecommerce.entity.Endereco;
import br.com.fiap.ecommerce.repository.EnderecoRepository;
import br.com.fiap.ecommerce.service.interfaces.IEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService implements IEnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    @Cacheable(value= "enderecoCache", key= "#id")
    public EnderecoDTO get(long id) {
        Endereco endereco = enderecoRepository.findById(id).get();
        return new EnderecoDTO(endereco);
    }

    @Override
    @Cacheable(value= "allEnderecosCache", unless= "#result.size() == 0")
    public List<EnderecoDTO> list(){
        List<EnderecoDTO> lista = new ArrayList<>();
        enderecoRepository.findAll().forEach(e -> lista.add(new EnderecoDTO(e)));
        return lista;
    }

    @Override
    @Caching(evict= { @CacheEvict(value= "allEnderecosCache", allEntries= true) })
    public EnderecoDTO create(EnderecoDTO enderecoDTO){
        Endereco endereco = enderecoRepository.save(new Endereco(enderecoDTO));
        return new EnderecoDTO(endereco);
    }

    @Override
    @Caching(
            put= { @CachePut(value= "enderecoCache", key= "#id") },
            evict= { @CacheEvict(value= "allEnderecosCache", allEntries= true) }
    )
    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco(enderecoDTO);
        endereco.setId(id);
        Endereco enderecoUpdated = enderecoRepository.save(endereco);
        return new EnderecoDTO(enderecoUpdated);
    }

    @Override
    @Caching(
            evict= {
                    @CacheEvict(value= "enderecoCache", key= "#id"),
                    @CacheEvict(value= "allEnderecosCache", allEntries= true)
            }
    )

    public void delete(long id) {
        enderecoRepository.delete(enderecoRepository.findById(id).get());
    }
}
