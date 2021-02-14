package br.com.tecsiscom.omapp.model.service.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsiscom.omapp.model.entity.produtos.Mercadoria;
import br.com.tecsiscom.omapp.model.repository.produtos.MercadoriaRepository;

@Service
public class MercadoriaService {

	@Autowired
	MercadoriaRepository mercadoriaRepository;
	
	public List<Mercadoria> listar(){

		List<Mercadoria> mercadorias =  mercadoriaRepository.findAll();
		return mercadorias;
	}

}
