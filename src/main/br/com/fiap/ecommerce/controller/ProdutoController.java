package br.com.fiap.ecommerce.controller;

import br.com.fiap.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ecommerce.dto.SaveProdutoDTO;

import br.com.fiap.ecommerce.utils.HTTPMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.fiap.ecommerce.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> list() {
        List<ProdutoDTO> lista = produtoService.list();
        return new ResponseEntity<List<ProdutoDTO>>(lista, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            ProdutoDTO produto = produtoService.get(id);
            return new ResponseEntity<ProdutoDTO>(produto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new HTTPMessageResponse("Nenhum produto com id={" + id + "} foi encontrado"));
        }
    }


    @PostMapping
    public ResponseEntity create(@RequestBody SaveProdutoDTO produto, UriComponentsBuilder builder) {
        try {
            ProdutoDTO createdProduto = produtoService.create(produto);
            return new ResponseEntity<ProdutoDTO>(createdProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel criar esse produto"));
        }
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody SaveProdutoDTO produto) {
        try {
            ProdutoDTO updatedProduto =  produtoService.update(id, produto);
            return new ResponseEntity<ProdutoDTO>(updatedProduto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel atualizar esse produto"));
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            produtoService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel deletar esse produto"));
        }
    }
}
