package br.com.tecsiscom.omapp.model.entity.veiculos;

public enum CTBCategoria {

	OFICIAL("Oficial"),
	DIPLOMATICOS("Diplomáticos"),
	PARTICULAR("Particular"),
	ALUGUEL("Aluguel"),
	APRENDIZAGEM("Aprendizagem");
	
	private String descricao;
	
	CTBCategoria(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}	
}
