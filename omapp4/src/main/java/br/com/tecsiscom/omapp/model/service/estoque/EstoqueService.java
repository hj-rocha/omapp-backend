package br.com.tecsiscom.omapp.model.service.estoque;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EstoqueNaoPodeFicarNegativoException;
import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.estoque.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository repository;
	
//	private Estoque salvar(Estoque estoque) {
//
//		Optional<Estoque> estq = Optional.ofNullable(this.repository.findByProdutoId(estoque.getProduto().getId()));
//		
//		Estoque e = new Estoque();
//		// Se o produto não foi cadastrado, cadastra já com a quantidade, se já foi, altera a quantidade.
//		if (estq.isEmpty()) {
//			e = this.repository.save(estoque);
//		} else {
//			e = estq.get();
//			// Adiciona a quantidade ao estoque atual
//			// Rever essa parte pois a tabela de estoque pode sofrer alteração entre a
//			// leitura da quantidade e
//			// a escrita da qtd atualizada. Rever se o @Transacitional trava a tabela para
//			// outras transações.
//			e.setQuantidade(e.getQuantidade() + estoque.getQuantidade());
//			// new BigDecimal((e.getQuantidade().doubleValue()) +
//			// (itemEntrada.getQuantidade().doubleValue())));
//
//			// Atualizar o estoque
//			this.repository.save(e);
//
//		}
//		
//		return repository.save(estoque);
//	}
//	
	
	
//	private void remover(Long id) {
//		repository.deleteById(id);
//	}
	
	
	private Estoque buscarEstoque(Produto produto) {
		Optional<Estoque> e = Optional.ofNullable(this.repository.findByProdutoId(produto.getId()));
		
		if(e.isPresent()) {
			return e.get();
		} else {	
			Estoque estoque = new Estoque();
			estoque.setProduto(produto);
			estoque.setQuantidade(0L);
			return this.repository.save(estoque); 
		}
	}
	
	public Estoque somarItem(Produto produto, Long qtd ){
		Estoque e = this.buscarEstoque(produto);
		e.setQuantidade(e.getQuantidade() + qtd);
		return this.repository.save(e);
	}
	
	public Estoque subtrairItem(Produto produto, Long qtd){
		Estoque e = this.buscarEstoque(produto);
		if((e.getQuantidade()-qtd)<0 ) {
			throw new EstoqueNaoPodeFicarNegativoException("O estoque não pode ficar negativo");
		}
		e.setQuantidade(e.getQuantidade() - qtd);
		return this.repository.save(e);
	}
}
