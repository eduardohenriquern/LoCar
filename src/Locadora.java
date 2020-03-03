import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Locadora {

	private List<Reserva> reservas;
	private double precoDiarioHatch = 120.00;
	private double precoDiarioSedan = 150.00;
	private double porcentagemMulta = 0.10;

	public Locadora() {
		this.reservas = new LinkedList<Reserva>();
	}

	/**
	 * Este método cria uma reserva e a adiciona em uma lista de reservas.
	 * @param veiculo
	 * @param cliente
	 * @param dataLoc
	 * @param dataDev
	 * @throws VeiculoReservadoException é lançada, caso o veículo já tenha sido reservado
	 */
	public void realizarReserva(Veiculo veiculo, Cliente cliente, String dataLoc, String dataDev) throws VeiculoReservadoException{

		if(verificarDisponibilidade(veiculo)) {

			Reserva reserva = new Reserva(veiculo, cliente, dataLoc, dataDev);
			this.reservas.add(reserva);
			System.out.println("Veículo " + veiculo.getNome() +" de placa "
					+veiculo.getPlaca()+" reservado por "+ cliente.getNome()+"\n");

		} else {
			throw new VeiculoReservadoException("O "+veiculo.getNome()+" de placa " + 
					veiculo.getPlaca() + " já foi reservado!");
		}
	}

	/**
	 * Este método finaliza uma reserva recebendo como parâmetros a
	 * reserva que será finalizada e a data de entreda do veículo.
	 * Caso haja atraso, é verificada a quantidade de dias e é emitida a multa.
	 * @param res
	 * @param dataEnt 
	 */
	public void finalizarReserva(Reserva res, String dataEnt) {
		
		String dataDev = res.getDataDevolucao();

		if (verificarSeEntregouComAtraso(dataDev, dataEnt)) {
			System.out.println(res.getCliente().getNome() + " entregou o veículo "+
					res.getVeiculo().getNome() + " com atraso!");
			
			// métodos responsáveis por calcular os dias de atraso, a multa e a emissão de multa
			int diasAtraso = subtrairDatas(conversorData(dataDev), conversorData(dataEnt));
			double multa = calcularMulta(res, diasAtraso);
			emitirMulta(multa);
			
		} else {
			System.out.println("O cliente "+ res.getCliente().getNome() + " entregou o veículo " + 
					res.getVeiculo().getNome() + " dentro do prazo!\n");
		}
		// Remove a reserva da lista
		this.reservas.remove(res);
	}

	/**
	 * Este método calcula a multa pelo atraso de locação considerando
	 * os dias de atraso e o preço de locação do veículo.
	 * @param res
	 * @return multa
	 */
	public double calcularMulta(Reserva res, int diasAtraso) {
		double multa;
		if(res.getVeiculo().getModelo().equals(Modelo.HATCH)) {
			multa = getPrecoDiarioHatch() + ((getPrecoDiarioHatch() * this.porcentagemMulta) * diasAtraso);
		} else {
			multa = getPrecoDiarioSedan() + ((getPrecoDiarioSedan() * this.porcentagemMulta) * diasAtraso);
		}

		return multa;
	}

	/**
	 * Este método verifica se o veículo foi entregue com atraso 
	 * @param dataDev
	 * @param dataEnt
	 * @return true se o veículo foi entregue com atraso
	 * @return false se o veículo não foi entregue com atraso
	 */
	public boolean verificarSeEntregouComAtraso(String dataDev, String dataEnt) {

		// retorna dois objetos do tipo Date
		Date dataDevolucao = conversorData(dataDev);
		Date dataEntrega = conversorData(dataEnt);
		
		// compara se data de entrega sucede a data que o veículo deveria ser devolvido
		if(dataEntrega.after(dataDevolucao)) {
			return true;
		} else {
			return false;
		} 
	} 

	/**
	 * 
	 * @param multa
	 */
	public void emitirMulta(double multa) {
		System.out.println("Pelo atraso, deverá pagar R$ " + multa +"\n");
	}

	/**
	 * Este método realiza a conversão das datas recebendo um String e retornando um Date
	 * @param dataTexto
	 * @return date
	 */
	public Date conversorData(String dataTexto) {
		Date dataCalendar = new Date();

		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
		} catch (ParseException e) {
			System.out.println("Erro ao converter a data!" + e.getClass());
		}
		return dataCalendar;
	}


	/**
	 * Este método subtrai a data que o cliente deveria
	 * devolver o veículo pela data que o mesmo devolveu.
	 * E salva na variável diasAtraso
	 * @param dataDev
	 * @param dataEnt
	 */
	public int subtrairDatas(Date dataDev, Date dataEnt) {
		Calendar dataDevolucao = Calendar.getInstance();
		dataDevolucao.setTime(dataDev);

		Calendar dataEntrega = Calendar.getInstance();
		dataDevolucao.setTime(dataEnt);

		dataDevolucao.add(Calendar.DATE, - dataEntrega.get(Calendar.DAY_OF_MONTH));
		return dataDevolucao.get(Calendar.DAY_OF_MONTH); // retorna os dias em atraso
	}


	/**
	 * Este método verifica se um veículo está disponível para locação
	 * @param veiculo
	 * @return true se estiver disponível
	 * @return false se não estiver disponível
	 */
	public boolean verificarDisponibilidade(Veiculo veiculo) {
		for(Reserva res: getReservas()) {
			if(res.getVeiculo().getPlaca().equals(veiculo.getPlaca())) {
				return false;
			} 
		}
		return true;
	}


	public double getPrecoDiarioHatch() {
		return precoDiarioHatch;
	}

	public void setPrecoDiarioHatch(double precoDiarioHatch) {
		this.precoDiarioHatch = precoDiarioHatch;
	}

	public double getPrecoDiarioSedan() {
		return precoDiarioSedan;
	}

	public void setPrecoDiarioSedan(double precoDiarioSedan) {
		this.precoDiarioSedan = precoDiarioSedan;
	}

	public List<Reserva> getReservas(){
		return this.reservas;
	}

}
