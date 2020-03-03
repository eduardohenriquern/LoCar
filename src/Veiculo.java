
public class Veiculo {
	private String nome;
	private String placa;
	private String ano;
	private String fabricante;
	private Modelo modelo;
	private String cor;
	
	public Veiculo(String n, String p, String a, String f, String c, Modelo m) {
		this.ano = a;
		this.cor = c;
		this.fabricante = f;
		this.modelo = m;
		this.placa = p;
		this.nome = n;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
