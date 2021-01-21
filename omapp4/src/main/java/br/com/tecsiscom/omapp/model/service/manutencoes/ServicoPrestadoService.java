package br.com.tecsiscom.omapp.model.service.manutencoes;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.exception.EntidadeEmUsoException;
import br.com.tecsiscom.omapp.exception.ProdutoNaoEncontradoException;
import br.com.tecsiscom.omapp.exception.UsuarioNaoEncontradoException;
import br.com.tecsiscom.omapp.model.entity.manutencoes.ServicoPrestado;
import br.com.tecsiscom.omapp.model.entity.pessoas.Usuario;
import br.com.tecsiscom.omapp.model.repository.manutencoes.ServicoPrestadoRepository;

@Service
public class ServicoPrestadoService {

	@Autowired
	ServicoPrestadoRepository servicoPrestadoRepository;
	
	public ServicoPrestado salvar(ServicoPrestado servicoPrestado) {
		
		return servicoPrestadoRepository.save(servicoPrestado);
	}
	
	public void remover(Long servicoPrestadoId) {
		try {
			servicoPrestadoRepository.deleteById(servicoPrestadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(servicoPrestadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Serviço prestado utilizada de código %d não pode ser removido, pois está em uso", servicoPrestadoId));
		}
	}
	
	
	public ServicoPrestado buscarOuFalhar(Long servicoPrestadoId) {
		return servicoPrestadoRepository.findById(servicoPrestadoId)
			.orElseThrow(() -> new UsuarioNaoEncontradoException(servicoPrestadoId));
	}
	
	
	public void entregarServico(Long servicoPrestadoId) {
		
		ServicoPrestado s = this.buscarOuFalhar(servicoPrestadoId);
		
		s.setDataEntrega(LocalDateTime.now());
		
		servicoPrestadoRepository.save(s);
	}
}
