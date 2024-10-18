
import java.util.Calendar;

public class Documento {

	private Long id;
	private Calendar data;
	private int idAluno;
	private String nomeAluno;
	private String descricao;
	private String conteudo;
	private boolean upload;

	public Documento() {

	}

	public Documento(Long id, Calendar data, int idAluno, String nomeAluno, String descricao, String conteudo,
			boolean upload) {
		this.id = id;
		this.data = data;
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.upload = upload;
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

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	@Override
	public String toString() {
		return "Documento id = " + id + ", data = " + data + ", idAluno = " + idAluno + ", nomeAluno = " + nomeAluno
				+ ", descricao = " + descricao + ", conteudo = " + conteudo + ", upload = " + upload;
	}
	

}
