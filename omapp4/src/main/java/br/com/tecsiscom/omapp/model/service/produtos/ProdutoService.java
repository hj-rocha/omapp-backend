package br.com.tecsiscom.omapp.model.service.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.produtos.Imposto;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ImpostoService impostoService;

	public List<Produto> listar(){

		List<Produto> produtos =  produtoRepository.findAll();
		return produtos;
	}
	
	
	public Produto salvar(Produto produto) {
//		boolean exists = produtoRepository.existsByNome(produto.getNome());
//		if (exists) {
//			throw new ProdutoCadastradoException(produto.getNome());
//		}
		
		return produtoRepository.save(produto);
	}
	
	public void remover(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Produto de código %d não pode ser removido, pois está em uso", produtoId));
		}
	}
	
	@Transactional
	public void desassociarImposto(Long produtoId, Long impostoId) {
		Produto produto = buscarOuFalhar(produtoId);
		Imposto imposto = impostoService.buscarOuFalhar(impostoId);
		
		produto.removerImposto(imposto);
		produtoRepository.save(produto);
	}
	
	@Transactional
	public void associarImposto(Long produtoId, Long impostoId) {
		Produto produto = buscarOuFalhar(produtoId);
		Imposto imposto = impostoService.buscarOuFalhar(impostoId);
		
		produto.adicionarImposto(imposto);
		
		produtoRepository.save(produto);
	}
	
	public Produto buscarOuFalhar(Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
		
	}


}
