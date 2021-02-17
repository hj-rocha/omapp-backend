package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.vendas;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.VendaNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.VendaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.ItemVenda;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.VendaRepository;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.ItemVendaService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.VendasService;
import br.com.tecsiscom.omapp.model.service.veiculos.VeiculoService;
import br.com.tecsiscom.omapp.rest.model.input.InputItemVendaVeiculo;
import br.com.tecsiscom.omapp.rest.model.input.InputItemVendaVeiculo;

@RestController
@RequestMapping(path = "/vendas/veiculo")
public class VeiculoVendaController {

	
	@Autowired
	VendasService service;
	
	@Autowired
	VendaRepository repository;

	@Autowired
	ItemVendaService itemVendaService;
	

	@Autowired
	VeiculoService veiculoService;

	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Transactional
	@CheckSecurity.Vendas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InputItemVendaVeiculo salvar(@RequestBody InputItemVendaVeiculo item) {

		Venda venda = new Venda();
		venda = item.getVenda();
		ItemVenda itemVenda = new ItemVenda();
		Optional<Produto> produto = this.produtoRepository.findById(item.getVeiculo().getId());
		itemVenda.setProduto(produto.get());
		itemVenda.setQuantidade(new BigDecimal(1));
		itemVenda.setValorUnitario(item.getValorUnitario());
		itemVenda.setId(item.getId());

		try {
			venda = service.salvar(item.getVenda());
			itemVenda.setVenda(venda);
			itemVenda = itemVendaService.salvar(itemVenda);
			item.setVenda(venda);

			return item;
		} catch(VendaNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping()
	public List<Venda> listar() {
		List<Venda> vendas = repository.findAll();
		
		for (Iterator<Venda> iterator = vendas.iterator(); iterator.hasNext();) {
			Venda venda = (Venda) iterator.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(venda.getConferente().getId());
			venda.setConferente(pessoa);
			pessoa.setId(venda.getVendedor().getId());
			venda.setVendedor(pessoa);
			pessoa.setId(venda.getCliente().getId());
			venda.setCliente(pessoa);
			vendas.indexOf(venda);
			
		}
		return vendas;
	}
	
}
