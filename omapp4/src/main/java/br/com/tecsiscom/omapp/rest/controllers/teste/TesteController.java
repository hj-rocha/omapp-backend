package br.com.tecsiscom.omapp.rest.controllers.teste;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecsiscom.omapp.core.security.CheckSecurity;
import br.com.tecsiscom.omapp.model.service.teste.Teste2Service;

@RestController
@RequestMapping("/testes")
public class TesteController {


	@Autowired
	private Teste2Service teste2Service;



	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping
	public String buscar() {

		return this.teste2Service.name("X");	
		}
	
	@CheckSecurity.Produtos.PodeConsultar
	@GetMapping("/big")
	public BigDecimal buscarBigDecimal() {
		
		BigDecimal qtdAnterior  = new BigDecimal("10.23");
		
		Double a = qtdAnterior.doubleValue(); 
		
		BigDecimal qtdEntrada = new BigDecimal("20.10");
		
		Double b = qtdEntrada.doubleValue(); 
		
		BigDecimal qtd = new BigDecimal(a+b);
		qtd.add(qtdAnterior);
		qtd.add(qtdEntrada);
		return qtd ;	
		}

}

