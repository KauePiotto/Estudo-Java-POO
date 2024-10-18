
public enum StatusAluno {
	ATIVO("Ativo"), 
	INATIVO("Inativo"), 
	SUSPENSO("Suspenso");

	String descricao;
	int ordem;

	StatusAluno(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
