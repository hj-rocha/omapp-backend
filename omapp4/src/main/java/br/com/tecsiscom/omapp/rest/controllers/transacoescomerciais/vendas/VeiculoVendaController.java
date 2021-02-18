package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.vendas;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.VendaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.ItemVenda;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.ItemVendaRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.VendaRepository;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.ItemVendaService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.VendasService;
import br.com.tecsiscom.omapp.model.service.veiculos.VeiculoService;
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
	
	@Autowired
	ItemVendaRepository itemVendaRepository;
	
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

		return vendas;
	}

	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping("/{vendaId}")
	public InputItemVendaVeiculo buscar(@PathVariable Long vendaId) {
		Optional<Venda> venda = repository.findById(vendaId);
		InputItemVendaVeiculo item = new InputItemVendaVeiculo();
		item.setVenda(venda.get());
		Optional<ItemVenda> itemVenda = Optional.ofNullable(this.itemVendaRepository.findTop1ByVendaId(vendaId));
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(itemVenda.get().getProduto().getId());
		item.setId(itemVenda.get().getId());
		item.setQuantidade(itemVenda.get().getQuantidade());
		item.setValorUnitario(itemVenda.get().getValorUnitario());
		item.setVeiculo(veiculo.get());
		return item;
	}
	
	@Transactional
	@CheckSecurity.Vendas.PodeEditar
	@DeleteMapping("/{vendaId}")
	public ResponseEntity<Venda> remover(@PathVariable("vendaId") Long id) {

		try {
			Long idItemVendaVeiculo = (itemVendaRepository.findTop1ByVendaId(id)).getId();
			itemVendaService.remover(idItemVendaVeiculo);
			service.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	
}
