package br.com.fiap.ecommerce.service;

import br.com.fiap.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ecommerce.dto.SaveProdutoDTO;
import br.com.fiap.ecommerce.entity.Cliente;
import br.com.fiap.ecommerce.repository.ProdutoRepository;
import br.com.fiap.ecommerce.service.interfaces.IProdutoService;

import br.com.fiap.ecommerce.entity.Produto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Cacheable(value= "produtoCache", key= "#id")
    public ProdutoDTO get(long id) {
        Produto produto = produtoRepository.findById(id).get();
        return new ProdutoDTO(produto);
    }

    @Override
    @Cacheable(value= "allProdutosCache", unless= "#result.size() == 0")
    public List<ProdutoDTO> list(){
        List<ProdutoDTO> lista = new ArrayList<>();
        produtoRepository.getAllProdutos().forEach(p -> lista.add(new ProdutoDTO(p)));
        return lista;
    }

    @Override
    @Caching(evict= { @CacheEvict(value= "allProdutosCache", allEntries= true) })
    public ProdutoDTO create(SaveProdutoDTO saveProdutoDTO){
        Produto produto = produtoRepository.save(new Produto(saveProdutoDTO));
        return new ProdutoDTO(produto);
    }

    @Override
    @Caching(
            put= { @CachePut(value= "produtoCache", key= "#id") },
            evict= { @CacheEvict(value= "allProdutosCache", allEntries= true) }
    )
    public ProdutoDTO update(Long id, SaveProdutoDTO saveProdutoDTO) {
        Produto produto = new Produto(saveProdutoDTO);
        produto.setId(id);
        Produto produtoUpdated = produtoRepository.save(produto);
        return new ProdutoDTO(produtoUpdated);
    }

    @Override
    @Caching(
            evict= {
                    @CacheEvict(value= "produtoCache", key= "#id"),
                    @CacheEvict(value= "allProdutosCache", allEntries= true)
            }
    )

    public void delete(long id) {
        Produto produto = produtoRepository.getById(id);
        produto.setDeletedAt(LocalDate.now());
        produtoRepository.save(produto);
    }
}
