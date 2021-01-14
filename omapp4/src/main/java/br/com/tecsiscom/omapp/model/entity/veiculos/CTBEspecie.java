package br.com.tecsiscom.omapp.model.entity.veiculos;

public enum CTBEspecie {

	MOTONETA("Motoneta"),
	MOTOCICLETA("Motocicleta"),
	TRICICLO("Triciclo"),
	QUADRICICLO("Quadriciclo"),
	AUTOMOVEL("Automóvel"),
	MICROONIBUS("Microônibus"),
	ONIBUS("Ônibus"),
	CAMINHONETE("Caminhonete"),
	CAMINHAO("Caminhão"),
	UTILITARIO("Utilitário"),
	DECOMPETICAO("De Competição"),
	TRATOR("Trator"),
	REBOQUE("Reboque");
	
	private String descricao;
	
	CTBEspecie(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
