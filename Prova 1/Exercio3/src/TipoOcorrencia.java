
public enum TipoOcorrencia {
//Frequência, Comportamento, Saúde Mental, Administrativa ou Outras
	FREQUENCIA("Frequência"),
	COMPORTAMENTO("Comportamento"),
	SAUDEMENTAL("Saúde Mental"),
	ADMINISTRATIVA("Administrativa"),
	OUTRAS("Outras");
	String descricao;
	int ordem;

	TipoOcorrencia(String descricao) {
		this.descricao = descricao;
		
	}

	@Override
	public String toString() {
		return descricao;
	}
}
