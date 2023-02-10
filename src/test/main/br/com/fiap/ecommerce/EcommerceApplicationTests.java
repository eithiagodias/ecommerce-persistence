package br.com.fiap.ecommerce;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import br.com.fiap.ecommerce.dto.ClienteDTO;
import br.com.fiap.ecommerce.dto.SaveItemPedidoDTO;
import br.com.fiap.ecommerce.dto.SavePedidoDTO;
import br.com.fiap.ecommerce.entity.*;
import br.com.fiap.ecommerce.entity.model.Sexo;
import br.com.fiap.ecommerce.entity.model.UF;
import br.com.fiap.ecommerce.repository.*;

import br.com.fiap.ecommerce.service.ClienteService;
import br.com.fiap.ecommerce.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcommerceApplicationTests {

	private String cpf = "46480944100";
	private String codigoProduto = "PR0002";

	@Autowired
	public ClienteService clienteService;

	@Autowired
	public PedidoService pedidoService;

	@Autowired
	public ProdutoRepository produtoRepository;


	@Test
	public void createCliente() {

		try {
			ClienteDTO thiago = new ClienteDTO();
			thiago.setNome("Thiago Rodrigues Dias");
			thiago.setCpf(cpf);
			thiago.setSexo(Sexo.MASCULINO);
			thiago.setTelefone("55119537100");
			thiago.setDataNascimento(LocalDate.now().minusYears(22));

			Endereco enderecoEntrega = new Endereco();
			enderecoEntrega.setCep("09784435");
			enderecoEntrega.setLogradouro("Rua Resende");
			enderecoEntrega.setNumero("8");
			enderecoEntrega.setBairro("S. Pedro");
			enderecoEntrega.setCidade("S. Bernardo do Campo");
			enderecoEntrega.setUf(UF.SP);

			List<Endereco> enderecosList = new LinkedList<Endereco>();
			enderecosList.add(enderecoEntrega);
			thiago.setEnderecos(enderecosList);
			clienteService.create(thiago);

			System.out.println("Cliente criado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao criar dados");
			System.out.println("Error: " + e.getMessage());
		}

	}

	@Test
	public void createData() {

		try {
			Produto produto = new Produto();
			produto.setCodigo(codigoProduto);
			produto.setNome("Bola de Futebol");
			produto.setValor(100);
			produto.setQuantidade(10);
			produtoRepository.saveAndFlush(produto);

			List<SaveItemPedidoDTO> itensPedido = new LinkedList<>();
			itensPedido.add(new SaveItemPedidoDTO(produto.getId(), 5));
			SavePedidoDTO savePedidoDTO = new SavePedidoDTO(cpf, itensPedido);
			pedidoService.create(savePedidoDTO);

			System.out.println("Dados criados com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao criar dados");
			System.out.println("Error: " + e.getMessage());
		}
	}
}
