
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogAcesso {

	private Long id;
	private Calendar data;
	private Usuario usuario;
	private String ip;

	
	
	public LogAcesso() {
		
	}

	public LogAcesso(Long id, Calendar data, Usuario usuario, String ip) {
		this.id = id;
		this.data = data;
		this.usuario = usuario;
		this.ip = ip;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDataFormatada() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		return formatador.format(getData().getTime());
	}

	@Override
	public String toString() {
		return "LogAcesso id = " + id + ", data = " + data + ", usuario = " + usuario + ", ip = " + ip;
	}
	

}
