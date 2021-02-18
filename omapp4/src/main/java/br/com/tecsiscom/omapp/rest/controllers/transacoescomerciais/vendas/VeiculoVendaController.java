package br.com.tecsiscom.omapp.rest.controllers.transacoescomerciais.vendas;

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
import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.EntidadeNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.NegocioException;
import br.com.tecsiscom.omapp.exception.VendaNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.estoque.saida.ItemSaida;
import br.com.tecsiscom.omapp.model.entity.estoque.saida.Saida;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ContaReceber;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ItemContaReceber;
import br.com.tecsiscom.omapp.model.entity.manutencoes.Manutencao;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialSaida;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.ItemVenda;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import br.com.tecsiscom.omapp.model.repository.estoque.EstoqueRepository;
import br.com.tecsiscom.omapp.model.repository.financeiro.receber.ContaReceberRepository;
import br.com.tecsiscom.omapp.model.repository.financeiro.receber.ItemContaReceberRepository;
import br.com.tecsiscom.omapp.model.repository.manutencoes.ManutencaoRepository;
import br.com.tecsiscom.omapp.model.repository.pessoas.PessoaRepository;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.ItemVendaRepository;
import br.com.tecsiscom.omapp.model.repository.transacoescomerciais.vendas.VendaRepository;
import br.com.tecsiscom.omapp.model.repository.veiculos.VeiculoRepository;
import br.com.tecsiscom.omapp.model.service.estoque.EstoqueService;
import br.com.tecsiscom.omapp.model.service.estoque.saida.ItemSaidaService;
import br.com.tecsiscom.omapp.model.service.estoque.saida.SaidaService;
import br.com.tecsiscom.omapp.model.service.financeiro.receber.ContasReceberService;
import br.com.tecsiscom.omapp.model.service.financeiro.receber.ItemContaReceberService;
import br.com.tecsiscom.omapp.model.service.manutencoes.ManutencaoService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.ItemVendaService;
import br.com.tecsiscom.omapp.model.service.transacoescomerciais.vendas.VendasService;
import br.com.tecsiscom.omapp.model.service.veiculos.VeiculoService;
import br.com.tecsiscom.omapp.rest.model.input.InputDespachamentoVendaVeiculo;
import br.com.tecsiscom.omapp.rest.model.input.InputItemVendaVeiculo;
import br.com.tecsiscom.omapp.rest.model.output.OutputDespachamentoVendaVeiculo;

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

	@Autowired
	SaidaService saidaService;

	@Autowired
	ItemSaidaService itemSaidaService;

	@Autowired
	ManutencaoService manutencaoService;

	@Autowired
	EstoqueRepository estoqueRepository;

	@Autowired
	ContasReceberService contaReceberService;

	@Autowired
	ItemContaReceberService itemContaReceberService;

	@Autowired
	ItemContaReceberRepository itemContaReceberRepository;

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	ManutencaoRepository manutencaoRepository;

	@Autowired
	EstoqueService estoqueService;

	@Autowired
	ContaReceberRepository contaReceberRepository;

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
		venda.setTotal(item.getValorUnitario());

		try {
			venda = service.salvar(venda);
			itemVenda.setVenda(venda);
			itemVenda = itemVendaService.salvar(itemVenda);
			item.setVenda(venda);

			return item;
		} catch (VendaNaoEncontradoException e) {
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

	@CheckSecurity.Vendas.PodeConsultar
	@GetMapping("/completa/{vendaId}")
	public OutputDespachamentoVendaVeiculo buscarTudo(@PathVariable Long vendaId) {
		Optional<Venda> venda = repository.findById(vendaId);
		Optional<ItemVenda> itemVenda = Optional.ofNullable(this.itemVendaRepository.findTop1ByVendaId(vendaId));
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(itemVenda.get().getProduto().getId());

		/* Empcotar o retorno */
		OutputDespachamentoVendaVeiculo out = new OutputDespachamentoVendaVeiculo();
		out.setVenda(venda.get());
		out.setVeiculo(veiculo.get());
		if (venda.get().getProcessada()) {
			ContaReceber cr = this.contaReceberRepository.findByVendaId(vendaId);
			out.setContaReceber(cr);
			out.setItensContaReceber(this.itemContaReceberRepository.findByContaReceberId(cr.getId()));
			out.setEstoque(this.estoqueRepository.findByProdutoId(veiculo.get().getId()));
			// out.setIdManutencao(this.manutencaoRepository.findByVendaId(vendaId).getId());
			/* Empcotar o retorno */
		}
		return out;
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

	@Transactional
	@CheckSecurity.Vendas.PodeEditar
	@PostMapping(path = "/despachar")
	@ResponseStatus(HttpStatus.CREATED)
	public OutputDespachamentoVendaVeiculo despacharVendaVeiculo(@RequestBody InputDespachamentoVendaVeiculo item) {

		try {
			/* Saida */
			Saida saida = new Saida();
			TransacaoComercialSaida t = new TransacaoComercialSaida();
			t.setId(item.getIdVenda());
			saida.setTransacaoComercialSaida(t);
			saida = saidaService.salvar(saida);

			ItemVenda icr = this.itemVendaRepository.findTop1ByVendaId(item.getIdVenda());
			Produto p = icr.getProduto();
//			Optional<Estoque> estoque = Optional.ofNullable(this.estoqueRepository.findByProdutoId(p.getId()));
//
//			/* RN:56 */
//			if (estoque.isPresent()) {
//				if (estoque.get().getQuantidade() > 0) {
//					throw new EstoqueVeiculoNaoUnitarioException(
//							"O estoque de um veículo "
//									+ estoque.get().getProduto().getNome()
//									+" não pode ser maior que um.");
//				}
//			}

			ItemSaida i = new ItemSaida();
			i.setSaida(saida);
			i.setQuantidade(1L);
			i.setProduto(p);
			/* Atualiza o estoque */
			this.estoqueService.subtrairItem(p, 1L);
			/* Salva o item */
			i = this.itemSaidaService.salvar(i);
			/* Saida */

			/* Contas Receber */
			ContaReceber cr = new ContaReceber();
			Optional<Venda> c;
			c = this.repository.findById(item.getIdVenda());
			cr.setVenda(c.get());
			cr.setDevedor(c.get().getCliente());
			cr = this.contaReceberService.salvar(cr);

			ItemContaReceber ict = new ItemContaReceber();
			ict.setContaReceber(cr);
			BigDecimal totalVenda = icr.getValorUnitario();
			ict.setValorDocumento(totalVenda);
			ict.setDataVencimento(item.getDataPrimeiraParcela());
			ict = this.itemContaReceberService.salvar(ict);
			/* Contas Receber */

			/* Atualizar Status Venda */
			Optional<Venda> venda = this.repository.findById(item.getIdVenda());
			venda.get().setProcessada(true);
			this.service.salvar(venda.get());
			/* Atualizar Status Venda */

			/* Fechar Manutenção */
			Optional<Veiculo> v = this.veiculoRepository.findById(icr.getProduto().getId());
			Manutencao m = this.manutencaoRepository.findByVeiculoIdAndAtiva(v.get().getId(), true);
			m.setVenda(venda.get());
			m = manutencaoService.salvar(m);
			m = this.manutencaoService.alterarStatusManutencao(m.getId(), false);
			/* Abrir Manutenção */

			/* Empcotar o retorno */
			OutputDespachamentoVendaVeiculo out = new OutputDespachamentoVendaVeiculo();
			out.setVenda(venda.get());
			out.setVeiculo(v.get());
			out.setContaReceber(cr);
			Set<ItemContaReceber> itensReceber = new HashSet<ItemContaReceber>();
			itensReceber.add(ict);
			out.setItensContaReceber(itensReceber);
			out.setIdManutencao(m.getId());
			/* Empcotar o retorno */

			return out;

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage()) {
				private static final long serialVersionUID = 1L;
			};
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Venda %d já foi despachada.", item.getIdVenda()));
		}
	}

}
