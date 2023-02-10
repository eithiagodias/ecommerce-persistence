package br.com.fiap.ecommerce.controller;

import br.com.fiap.ecommerce.dto.ClienteDTO;

import br.com.fiap.ecommerce.utils.HTTPMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity list(){
        try {
            List<ClienteDTO> clientesDTO = clienteService.list();
            return ResponseEntity.status(HttpStatus.OK).body(clientesDTO);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new HTTPMessageResponse("Nenhum cliente encontrado"));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Long id){
        try {
            ClienteDTO cliente = clienteService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new HTTPMessageResponse("Nenhum cliente com id={" + id + "} foi encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ClienteDTO clienteDTO){
        try {
            clienteService.create(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel criar esse cliente {"+e.getMessage()+"}"));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel deletar esse cliente"));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        try {
            clienteService.update(id, clienteDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel atualizar esse cliente {"+e.getMessage()+"}"));
        }
    }

}
