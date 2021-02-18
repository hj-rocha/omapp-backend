package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.compras;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import br.com.tecsiscom.omapp.exception.EstoqueVeiculoNaoUnitarioException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.entity.estoque.entrada.Entrada;
import br.com.tecsiscom.omapp.model.entity.estoque.entrada.ItemEntrada;
import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ContaPagar;
import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ItemContaPagar;
import br.com.tecsiscom.omapp.model.entity.manutencoes.Manutencao;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialEntrada;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.ItemCompra;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.estoque.EstoqueRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.CompraRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.compras.ItemCompraRepository;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;
import br.com.tecsiscom.omapp.model.service.estoque.EstoqueService;
import br.com.tecsiscom.omapp.model.service.estoque.entrada.EntradaService;
import br.com.tecsiscom.omapp.model.service.estoque.entrada.ItemEntradaService;
import br.com.tecsiscom.omapp.model.service.financeiro.pagar.ContasPagarService;
import br.com.tecsiscom.omapp.model.service.financeiro.pagar.ItemContaPagarService;
import br.com.tecsiscom.omapp.model.service.manutencoes.ManutencaoService;
import br.com.tecsiscom.omapp.model.service.produtos.ProdutoService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras.CompraService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.compras.ItemCompraService;
import br.com.tecsiscom.omapp.model.service.veiculos.VeiculoService;
import br.com.tecsiscom.omapp.rest.model.input.InputItemCompraVeiculo;
import br.com.tecsiscom.omapp.rest.model.input.InputRecebimentoCompraVeiculo;
import br.com.tecsiscom.omapp.rest.model.output.OutputRecebimentoCompraVeiculo;

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
	ProdutoService produtoService;

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	EntradaService entradaService;

	@Autowired
	ItemEntradaService itemEntradaService;

	@Autowired
	ContasPagarService contaPagarSevice;

	@Autowired
	ItemContaPagarService itemContaPagarService;

	@Autowired
	ManutencaoService manutencaoService;

	@Autowired
	EstoqueRepository estoqueRepository;
	
	@Autowired
	EstoqueService estoqueService;

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
	@PostMapping(path = "/receber")
	@ResponseStatus(HttpStatus.CREATED)
	public OutputRecebimentoCompraVeiculo receberCompraVeiculo(@RequestBody InputRecebimentoCompraVeiculo item) {

		try {
			/* Entrada */
			Entrada entrada = new Entrada();
			TransacaoComercialEntrada t = new TransacaoComercialEntrada();
			t.setId(item.getIdCompra());
			entrada.setTransacaoComercialEntrada(t);
			entrada = entradaService.salvar(entrada);

			ItemCompra icp = this.itemCompraRepository.findTop1ByCompraId(item.getIdCompra());
			Produto p = icp.getProduto();
			Optional<Estoque> estoque = Optional.ofNullable(this.estoqueRepository.findByProdutoId(p.getId()));

			/* RN:56 */
			if (estoque.isPresent()) {
				if (estoque.get().getQuantidade() > 0) {
					throw new EstoqueVeiculoNaoUnitarioException(
							"O estoque de um veículo "
									+ estoque.get().getProduto().getNome()
									+" não pode ser maior que um.");
				}
			}

			ItemEntrada i = new ItemEntrada();
			i.setEntrada(entrada);
			i.setQuantidade(1L);
			i.setProduto(p);
			/* Atualiza o estoque*/
			this.estoqueService.somarItem(p, 1L);
			/*Salva o item*/
			i = this.itemEntradaService.salvar(i);
			/* Entrada */

			/* Contas Pagar */
			ContaPagar cp = new ContaPagar();
			Optional<Compra> c;
			c = this.compraRepository.findById(item.getIdCompra());
			cp.setCompra(c.get());
			cp.setCredor(c.get().getFornecedor());
			cp = this.contaPagarSevice.salvar(cp);

			ItemContaPagar ict = new ItemContaPagar();
			ict.setContaPagar(cp);
			BigDecimal totalCompra = icp.getValorUnitario();
			ict.setValorDocumento(totalCompra);
			ict.setDataVencimento(item.getDataPrimeiraParcela());
			ict = this.itemContaPagarService.salvar(ict);
			/* Contas Pagar */

			/* Atualizar Produto */
			p.setCusto(icp.getValorUnitario());
			p.getFornecedores().add(c.get().getFornecedor());
			p = this.produtoService.salvar(p);
			/* Atualizar Produto */

			/* Atualizar Status Compra */
			Optional<Compra> compra = this.compraRepository.findById(item.getIdCompra());
			compra.get().setProcessada(true);
			this.compraService.salvar(compra.get());
			/* Atualizar Status Compra */

			/* Abrir Manutenção */
			Optional<Veiculo> v = this.veiculoRepository.findById(icp.getProduto().getId());
			Optional<Pessoa> responsavel = this.pessoaRepository.findById(item.getIdConferenteLogado());
			Manutencao m = new Manutencao();
			m.setVeiculo(v.get());
			m.setResponsavelManutencao(responsavel.get());
			m = this.manutencaoService.salvar(m);
			/* Abrir Manutenção */

			/* Empacotar o retorno */
			OutputRecebimentoCompraVeiculo out = new OutputRecebimentoCompraVeiculo();
			out.setCompra(compra.get());
			out.setVeiculo(v.get());
			out.setContaPagar(cp);
			Set<ItemContaPagar> itensPagar = new HashSet<ItemContaPagar>();
			itensPagar.add(ict);
			out.setItensContaPgar(itensPagar);
			out.setIdManutencao(m.getId());
			/* Empacotar o retorno */

			return out;

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage()) {
				private static final long serialVersionUID = 1L;
			};
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Compra %d já está recebida.", item.getIdCompra()));
		}
	}

}
