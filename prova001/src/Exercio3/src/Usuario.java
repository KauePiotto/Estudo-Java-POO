

public class Usuario {

	private Long id;

	private String nif;
	private String nome;
	private String senha;
	private TipoUsuario tipoUsuario;
	// usuário pode ser Comum, Coordenador ou Administrador
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String nif, String nome, String senha, TipoUsuario tipoUsuario) {
		this.id = id;
		this.nif = nif;
		this.nome = nome;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setSenhaCrip(String senha) {
		this.senha = senha;
	}


	public boolean isAdmin() {
		return this.getTipoUsuario() == TipoUsuario.ADMINISTRADOR;
	}

	@Override
	public String toString() {
		return "Usuario id = " + id + ", nif = " + nif + ", nome = " + nome + ", senha = " + senha + ", tipoUsuario = "
				+ tipoUsuario;
	}
	

}
