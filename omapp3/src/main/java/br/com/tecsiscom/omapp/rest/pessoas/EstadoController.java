package br.com.tecsiscom.omapp.rest.pessoas;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.model.entity.pessoas.Estado;
import br.com.tecsiscom.omapp.model.repository.pessoas.EstadoRepository;
import br.com.tecsiscom.omapp.model.service.pessoas.EstadoService;

@RestController
@RequestMapping("estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService cadastroEstadoService;

	@CheckSecurity.Estados.PodeConsultar
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@CheckSecurity.Estados.PodeConsultar
	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable("estadoId") Long estadoId) {
		return cadastroEstadoService.buscarOuFalhar(estadoId);
	}

	@CheckSecurity.Estados.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado);
	}

	@CheckSecurity.Estados.PodeEditar
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("estadoId") Long id) {
			cadastroEstadoService.remover(id);
	}

	@CheckSecurity.Estados.PodeEditar
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable("estadoId") Long estadoId, @RequestBody Estado estado){
		Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);
		
			BeanUtils.copyProperties(estado, estadoAtual, "id");

			return cadastroEstadoService.salvar(estadoAtual);
	
	}
	
	
}
