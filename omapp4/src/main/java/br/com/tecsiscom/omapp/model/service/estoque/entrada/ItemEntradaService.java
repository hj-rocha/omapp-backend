package br.com.tecsiscom.omapp.model.service.estoque.entrada;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.entity.estoque.entrada.ItemEntrada;
import br.com.tecsiscom.omapp.model.repository.estoque.EstoqueRepository;
import br.com.tecsiscom.omapp.model.repository.estoque.entrada.ItemEntradaRepository;

@Service
public class ItemEntradaService {

	@Autowired
	private ItemEntradaRepository repository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public ItemEntrada salvar(ItemEntrada itemEntrada) {

		//Busca Estoque do produto do item a ser inserido
		Optional<Estoque> estoque = Optional
				.ofNullable(this.estoqueRepository.findByProdutoId(itemEntrada.getProduto().getId()));

		Estoque e = new Estoque();
		
		//Se o produto ainda já foi cadastrado no estoque coloca o esotque em e, senão cadastra.
		if (!estoque.isEmpty()) {
			e = estoque.get();
		} else {
			e.setProduto(itemEntrada.getProduto());
			e.setQuantidade(itemEntrada.getQuantidade());
			e = this.estoqueRepository.save(e);
		}
		
		//Adiciona a quantidade ao estoque atual
		//Rever essa parte pois a tabela de estoque pode sofrer aleteração entre a leitura da quantidade e 
		//a escrita da qtd atualizada. Rever se o @Transacitional trava a tabela para outras transações.
		e.setQuantidade(
				new BigDecimal((e.getQuantidade().doubleValue()) + (itemEntrada.getQuantidade().doubleValue())));

		//Atualizar o estoque
		this.estoqueRepository.save(e);

		//Salvar o item de entrada.
		return repository.save(itemEntrada);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
