package br.com.tecsiscom.omapp.model.service.produtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.PessoaNaoEncontradaException;
import br.com.tecsiscom.omapp.exception.ProdutoCadastradoException;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import br.com.tecsiscom.omapp.model.repository.produtos.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> listar(){

		List<Produto> produtos =  produtoRepository.findAll();
		return produtos;
	}
	
	
	public Produto salvar(Produto produto) {
//		boolean exists = produtoRepository.existsByNome(produto.getNome());
//		if (exists) {
//			throw new ProdutoCadastradoException(produto.getNome());
//		}
		
		boolean exists = produtoRepository.existsById(produto.getId());
		
		if (exists) {
			Optional<Produto> produtoBD = this.produtoRepository.findById(produto.getId());

			BeanUtils.copyProperties(produto, produtoBD.get(), "id", "dataCadastro");
			
			return produtoRepository.save(produtoBD.get());	
		}
		
		return produtoRepository.save(produto);
	}
	
	public void remover(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(produtoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Produto de código %d não pode ser removido, pois está em uso", produtoId));
		}
	}
}
