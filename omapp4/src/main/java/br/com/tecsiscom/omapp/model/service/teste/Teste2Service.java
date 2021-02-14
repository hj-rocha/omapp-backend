package br.com.tecsiscom.omapp.model.service.teste;

import org.springframework.stereotype.Service;

@Service
public class Teste2Service extends TesteService{

	

	//@Override
	public String name(String ola) {
		String aux = super.name("OLA2");
		//System.out.println("OLA2");
		//super.name("OLA2");
		return aux + " + OLA3";
	}
	
}
