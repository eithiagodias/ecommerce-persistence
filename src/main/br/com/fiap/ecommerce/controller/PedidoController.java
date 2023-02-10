package br.com.fiap.ecommerce.controller;

import br.com.fiap.ecommerce.dto.PedidoDTO;
import br.com.fiap.ecommerce.dto.SavePedidoDTO;
import br.com.fiap.ecommerce.dto.UpdatePedidoDTO;
import br.com.fiap.ecommerce.utils.HTTPMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fiap.ecommerce.service.PedidoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity list(){
        List<PedidoDTO> lista = pedidoService.list();
        return new ResponseEntity<List<PedidoDTO>>(lista, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            PedidoDTO pedidoDto = pedidoService.get(id);
            return new ResponseEntity<PedidoDTO>(pedidoDto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new HTTPMessageResponse("Nenhum pedido com id={" + id + "} foi encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody SavePedidoDTO pedido) {
        try {
            PedidoDTO pedidoDto = pedidoService.create(pedido);
            return new ResponseEntity<PedidoDTO>(pedidoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel criar esse pedido {"+e.getMessage()+"}"));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdatePedidoDTO statusPedidoDTO){
        try {
            pedidoService.update(id, statusPedidoDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel atualizar esse pedido {"+e.getMessage()+"}"));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            pedidoService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HTTPMessageResponse("Nao foi possivel deletar esse pedido"));
        }
    }

}
