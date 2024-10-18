
public enum TipoUsuario {
	// usuário pode ser Comum, Coordenador ou Administrador
	COMUM("Comum"),
	COORDENADOR("Coordenador"),
	ADMINISTRADOR("Administrador");
	String descricao;
	
	TipoUsuario(String descricao) {
		this.descricao = descricao;
		
	}

	@Override
	public String toString() {
		return descricao;
	}

}
