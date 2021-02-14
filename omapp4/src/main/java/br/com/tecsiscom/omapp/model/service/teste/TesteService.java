package br.com.tecsiscom.omapp.model.service.teste;

import org.springframework.stereotype.Service;

@Service
public class TesteService {

	public String name(String ola) {
		//System.out.println("OLA 1");

		return "OLA1 + || "+ola+"--";
	}


}
