package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.compras;

import java.math.BigDecimal;
import java.util.Iterator;
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
import br.com.tecsiscom.omapp.exception.CompraNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.ItemCompra;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.CompraRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.ItemCompraRepository;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras.CompraService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras.ItemCompraService;
import br.com.tecsiscom.omapp.model.service.veiculos.VeiculoService;
import br.com.tecsiscom.omapp.rest.model.input.InputItemCompraVeiculo;

@RestController
@RequestMapping(path = "/compras/veiculo")
public class CompraVeiculoController {

	@Autowired
	CompraService compraService;

	@Autowired
	ItemCompraService itemCompraService;

	@Autowired
	VeiculoService veiculoService;

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	CompraRepository compraRepository;

	@Autowired
	ItemCompraRepository itemCompraRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	PessoaRepository pessoaRepository;

	@Transactional
	@CheckSecurity.Compras.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InputItemCompraVeiculo salvar(@RequestBody InputItemCompraVeiculo item) {

		Compra compra = new Compra();
		compra = item.getCompra();
		ItemCompra itemCompra = new ItemCompra();
		Optional<Produto> produto = this.produtoRepository.findById(item.getVeiculo().getId());
		itemCompra.setProduto(produto.get());
		itemCompra.setQuantidade(new BigDecimal(1));
		itemCompra.setValorUnitario(item.getValorUnitario());
		itemCompra.setId(item.getId());

		try {
			compra = compraService.salvar(item.getCompra());
			itemCompra.setCompra(compra);
			itemCompra = itemCompraService.salvar(itemCompra);

			//Optional<Veiculo> veiculo = this.veiculoRepository.findById(item.getVeiculo().getId());
			Veiculo veiculo = item.getVeiculo();
			veiculo.setProprietarios(item.getVeiculo().getProprietarios());
			veiculo.setPlaca(item.getVeiculo().getPlaca());
			veiculo.setPlacaAnterior(item.getVeiculo().getPlacaAnterior());
			item.setVeiculo(veiculoService.salvar(veiculo));
			item.setCompra(compra);

			return item;
		} catch (CompraNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Compras.PodeConsultar
	@GetMapping("/{compraId}")
	public InputItemCompraVeiculo buscar(@PathVariable Long compraId) {
		Optional<Compra> compra = compraRepository.findById(compraId);
		InputItemCompraVeiculo item = new InputItemCompraVeiculo();
		item.setCompra(compra.get());
		Optional<ItemCompra> itemCompra = Optional.ofNullable(this.itemCompraRepository.findTop1ByCompraId(compraId));
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(itemCompra.get().getProduto().getId());
		item.setId(itemCompra.get().getId());
		item.setQuantidade(itemCompra.get().getQuantidade());
		item.setValorUnitario(itemCompra.get().getValorUnitario());
		item.setVeiculo(veiculo.get());
		return item;
	}
	
	
	@CheckSecurity.Compras.PodeConsultar
	@GetMapping()
	public List<Compra> listar() {
		return compraRepository.findAll();
	}
	
	@Transactional
	@CheckSecurity.Compras.PodeEditar
	@DeleteMapping("/{compraId}")
	public ResponseEntity<Compra> remover(@PathVariable("compraId") Long id) {

		try {
			Long idItemCompraVeiculo = (itemCompraRepository.findTop1ByCompraId(id)).getId();
			itemCompraService.remover(idItemCompraVeiculo);
			compraService.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@Transactional
	@CheckSecurity.Compras.PodeEditar
	@PostMapping("/receber")
	@ResponseStatus(HttpStatus.CREATED)
	public InputItemCompraVeiculo receberCompraVeiculo(@RequestBody InputItemCompraVeiculo item) {
		return item;
	
	}
	
}
