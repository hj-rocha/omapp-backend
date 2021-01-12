package br.com.tecsiscom.omapp.rest.controllers.geografico.enderecos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Cidade;
import br.com.tecsiscom.omapp.model.entity.geografia.enderecos.Estado;
import br.com.tecsiscom.omapp.model.repository.geografia.enderecos.CidadeRepository;
import br.com.tecsiscom.omapp.model.repository.geografia.enderecos.EstadoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecosController {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	

	@CheckSecurity.Enderecos.PodeConsultar
	@GetMapping("/estados/{estadoId}/cidades")
	public List<Cidade> todasCidadesdoEstado(@PathVariable Long estadoId) {
	
		List<Cidade> cidades = cidadeRepository.findByEstadoId(estadoId);
		
		return cidades;
	}
	
	@CheckSecurity.Enderecos.PodeConsultar
	@GetMapping("/estados/sigla/{estadoSigla}/cidades")
	public List<Cidade> todasCidadesdoEstadoPorSigla(@PathVariable String estadoSigla) {
	
		List<Cidade> cidades = cidadeRepository.findByEstadoSigla(estadoSigla);
		
		//List<String> cids = new ArrayList<String>();
		
//		for (Cidade cidade : cidades) {
//			cids.add(cidade.getNome());
//		}
		
		return cidades;
	}
	
	@CheckSecurity.Enderecos.PodeConsultar	
	@GetMapping("/estados")
	public List<Estado> todosEstados() {
	
		List<Estado> estados = estadoRepository.findAll();
		
		return estados;
	}
	
	
}
