
public enum Periodo {

	MANHA("Manhã"), 
	TARDE("Tarde"), 
	NOITE("Noite");

	String descricao;
	int ordem;

	Periodo(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
