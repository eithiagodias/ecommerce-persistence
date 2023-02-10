package br.com.fiap.ecommerce.controller;

import br.com.fiap.ecommerce.dto.EnderecoDTO;
import br.com.fiap.ecommerce.service.EnderecoService;
import br.com.fiap.ecommerce.utils.HTTPMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> list() {
        List<EnderecoDTO> lista = enderecoService.list();
        return new ResponseEntity<List<EnderecoDTO>>(lista, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            EnderecoDTO endereco = enderecoService.get(id);
            return new ResponseEntity<EnderecoDTO>(endereco, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new HTTPMessageResponse("Nenhum endereco com id={" + id + "} foi encontrado"));
        }
    }


    @PostMapping
    public ResponseEntity create(@RequestBody EnderecoDTO enderecoDTO) {
        try {
            EnderecoDTO endereco = enderecoService.create(enderecoDTO);
            return new ResponseEntity<EnderecoDTO>(endereco, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel criar esse endereco"));
        }
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EnderecoDTO enderecoDTO) {
        try {
            EnderecoDTO updatedEndereco = enderecoService.update(id, enderecoDTO);
            return new ResponseEntity<EnderecoDTO>(updatedEndereco, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel atualizar esse endereco"));
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            enderecoService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel deletar esse endereco"));
        }
    }
}
