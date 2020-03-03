
public class Reserva {
	private String dataLocacao;
	private String dataDevolucao;
	
	private Veiculo veiculo;
	private Cliente cliente;
	
	public Reserva(Veiculo v, Cliente c, String dataLoc, String dataDev) {
		this.dataDevolucao = dataDev;
		this.dataLocacao = dataLoc;
		
		this.cliente = c;
		this.veiculo = v;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
}
