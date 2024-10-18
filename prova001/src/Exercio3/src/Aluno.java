
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class Aluno {

	private Long id;

	private String nome;

	private String matricula;
	private String cpf;
	private String rg;
	private String telefone;
	private String celular;
	private String email;
	private String empresa;
	private Turma turma;
	private Calendar nascimento;
	private StatusAluno status;
	private Calendar dataStatus;

	public Aluno() {
		this.status = StatusAluno.ATIVO;
	}
	
	public Aluno(long id, String nome, String matricula, String cpf, String rg, String telefone, String celular,
			String email, String empresa, Turma turma, Calendar nascimento, StatusAluno status, Calendar dataStatus) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.empresa = empresa;
		this.turma = turma;
		this.nascimento = nascimento;
		this.status = status;
		this.dataStatus = dataStatus;
		this.status = StatusAluno.ATIVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getIdade() {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate nasc = LocalDate.parse(formatador.format(nascimento.getTime()));
		return Period.between(nasc, LocalDate.now()).getYears();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusAluno getStatus() {
		return status;
	}

	public void setStatus(StatusAluno status) {
		this.status = status;
	}

	public Calendar getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Calendar dataStatus) {
		this.dataStatus = dataStatus;
	}

	public boolean isInativo() {
		return this.getStatus() != StatusAluno.ATIVO;
	}

	@Override
	public String toString() {
		return "Aluno id = " + id + ", nome = " + nome + ", matricula = " + matricula + ", cpf = " + cpf + ", rg = "
				+ rg + ", telefone = " + telefone + ", celular = " + celular + ", email = " + email + ", empresa = "
				+ empresa + ", turma = " + turma + ", nascimento = " + nascimento + ", status = " + status
				+ ", dataStatus = " + dataStatus;
	}

}
