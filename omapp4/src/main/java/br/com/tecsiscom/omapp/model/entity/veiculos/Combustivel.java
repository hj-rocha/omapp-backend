package br.com.tecsiscom.omapp.model.entity.veiculos;

public enum Combustivel {

	GASOLINA("Gasolina"),
	ALCOOL("Álcool"),
	DIESEL("Diesel"),
	QUEROSENE("Querosene"),
	ALCOOLGASOLINA("Álcool/Gasolina");
	
	
	private String descricao;
	
	Combustivel(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
