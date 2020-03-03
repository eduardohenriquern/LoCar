
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	static String[] nomesVeiculos = new String[5];
	static String[] placasVeiculos = new String[5];
	static String[] anoVeiculos = new String[5];
	static String[] fabricantesVeiculos = new String[5];
	static String[] corVeiculos = new String[5];
	static Modelo[] modeloVeiculos = new Modelo[5];
	static List<Veiculo> veiculos = new LinkedList<Veiculo>();
	static List<Cliente> clientes = new LinkedList<Cliente>();
	static List<String> datasDev = new LinkedList<String>();

	public static void main(String[] args) {

		gerarVeiculos();
		gerarClientes();
		gerarDatas();
		Locadora locadora = new Locadora();

		System.out.println("========	Realizando as reservas	=========");
		// Realizando as reserva
		for (int j=0; j < veiculos.size(); j++) {
			try {
				locadora.realizarReserva(veiculos.get(j), clientes.get(j), datasLoc.get(j), datasDev.get(j));
			} catch (VeiculoReservadoException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("=======================================================");
		
		System.out.println("========	Finalizando as reservas	   =========");
		// Finalizando as reservas
		String data = "03/03/2020";
		
		for (int k=0; k < locadora.getReservas().size(); k++) {
			locadora.finalizarReserva(locadora.getReservas().get(k), data);
		}
		System.out.println("=======================================================");
	}


	/*
	 * Variáveis estáticas com informações de veículos, clientes e datas
	 * E métodos para gerar os objetos e popular as listas que serão usadas na main
	 */

	
	static List<String> datasLoc = new LinkedList<String>();

	public static void gerarDatas() {
		datasLoc.add("24/02/2020");
		datasLoc.add("29/02/2020");
		datasLoc.add("01/02/2020");
		datasLoc.add("04/02/2020");
		datasLoc.add("10/02/2020");

		datasDev.add("10/03/2020");
		datasDev.add("08/03/2020");
		datasDev.add("20/02/2020");
		datasDev.add("25/02/2020");
		datasDev.add("01/03/2020");

	}


	public static void gerarClientes() {
		clientes.add(new Cliente("Luis", "11111"));
		clientes.add(new Cliente("Raul", "22222"));
		clientes.add(new Cliente("Silvia", "33333"));
		clientes.add(new Cliente("Marisa", "44444"));
		clientes.add(new Cliente("Carlos", "55555"));
	}

	public static void gerarVeiculos() {
		nomesVeiculos[0] = "Gol";
		nomesVeiculos[1] = "Gran Siena";
		nomesVeiculos[2] = "Palio";
		nomesVeiculos[3] = "Onix";
		nomesVeiculos[4] = "Prisma";

		placasVeiculos[0] = "ERT1223";
		placasVeiculos[1] = "JKL4554";
		placasVeiculos[2] = "PIJ8976";
		placasVeiculos[3] = "OTG7865";
		placasVeiculos[4] = "GFJ2834";

		anoVeiculos[0] = "2015";
		anoVeiculos[1] = "2016";
		anoVeiculos[2] = "2013";
		anoVeiculos[3] = "2017";
		anoVeiculos[4] = "2018";

		fabricantesVeiculos[0] = "VW";
		fabricantesVeiculos[1] = "Fiat";
		fabricantesVeiculos[2] = "Fiat";
		fabricantesVeiculos[3] = "Chevrolet";
		fabricantesVeiculos[4] = "Chevrolet";

		corVeiculos[0] = "Preto";
		corVeiculos[1] = "Branco";
		corVeiculos[2] = "Vermelho";
		corVeiculos[3] = "Cinza";
		corVeiculos[4] = "Prata";

		modeloVeiculos[0] = Modelo.HATCH;
		modeloVeiculos[1] = Modelo.SEDAN;
		modeloVeiculos[2] = Modelo.HATCH;
		modeloVeiculos[3] = Modelo.SEDAN;
		modeloVeiculos[4] = Modelo.SEDAN;

		for(int i=0; i < 5; i++) {
			veiculos.add(new Veiculo(nomesVeiculos[i], placasVeiculos[i],
					anoVeiculos[i], fabricantesVeiculos[i], corVeiculos[i], modeloVeiculos[i]));
		}
	}

}
