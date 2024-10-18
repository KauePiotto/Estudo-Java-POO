
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ocorrencia {

	private Long id;
	private Calendar data;
	private Long idAluno;
	private String nomeAluno;
	private Long idTurma;
	private String turma;
	private String descricao;
	private Usuario usuario;
	private boolean restrita;
	private TipoOcorrencia tipo;
	// tipos de ocorrência podem ser Frequência, Comportamento, Saúde Mental, Administrativa ou Outras.
	
	public Ocorrencia() {

	}
	
	public Ocorrencia(Long id, Calendar data, Long idAluno, String nomeAluno, Long idTurma, String turma,
			String descricao, Usuario usuario, boolean restrita, TipoOcorrencia tipo) {
		this.id = id;
		this.data = data;
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.idTurma = idTurma;
		this.turma = turma;
		this.descricao = descricao;
		this.usuario = usuario;
		this.restrita = restrita;
		this.tipo = tipo;
	}


	public boolean isRestrita() {
		return restrita;
	}

	public void setRestrita(boolean restrita) {
		this.restrita = restrita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuarios) {
		this.usuario = usuarios;
	}

	public String getDataFormatada() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		return formatador.format(getData().getTime());
	}

	public TipoOcorrencia getTipo() {
		return tipo;
	}

	public void setTipo(TipoOcorrencia tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Ocorrencia id = " + id + ", data = " + data + ", idAluno = " + idAluno + ", nomeAluno = " + nomeAluno
				+ ", idTurma = " + idTurma + ", turma = " + turma + ", descricao = " + descricao + ", usuario = " + usuario
				+ ", restrita = " + restrita + ", tipo = " + tipo;
	}
	

}
