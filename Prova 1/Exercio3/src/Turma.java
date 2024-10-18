
import java.io.Serializable;

public class Turma implements Serializable {

	private Long id;
	private String nomeCurso;
	private int qtdSemestreDoCurso;
	private byte semestreEntrada;
	private short anoEntrada;
	private Periodo periodo;
	private String sufixo;
	// período da turma, que pode ser manhã, tarde ou noite
	
	public Turma() {
		
	}
	
	public Turma(Long id, String nomeCurso, int qtdSemestreDoCurso, byte semestreEntrada, short anoEntrada,
			Periodo periodo, String sufixo) {
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.qtdSemestreDoCurso = qtdSemestreDoCurso;
		this.semestreEntrada = semestreEntrada;
		this.anoEntrada = anoEntrada;
		this.periodo = periodo;
		this.sufixo = sufixo;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public int getQtdSemestreDoCurso() {
		return qtdSemestreDoCurso;
	}

	public void setQtdSemestreDoCurso(int qtdSemestreDoCurso) {
		this.qtdSemestreDoCurso = qtdSemestreDoCurso;
	}

	public byte getSemestreEntrada() {
		return semestreEntrada;
	}

	public void setSemestreEntrada(byte semestreEntrada) {
		this.semestreEntrada = semestreEntrada;
	}

	public short getAnoEntrada() {
		return anoEntrada;
	}

	public void setAnoEntrada(short anoEntrada) {
		this.anoEntrada = anoEntrada;
	}

	public String getSufixo() {
		return sufixo;
	}

	public void setSufixo(String sufixo) {
		this.sufixo = sufixo.toUpperCase();
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "Turma id = " + id + ", nomeCurso = " + nomeCurso + ", qtdSemestreDoCurso = " + qtdSemestreDoCurso
				+ ", semestreEntrada = " + semestreEntrada + ", anoEntrada = " + anoEntrada + ", periodo = " + periodo
				+ ", sufixo = " + sufixo;
	}

}
