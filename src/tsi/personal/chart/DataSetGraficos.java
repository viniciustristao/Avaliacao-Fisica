package tsi.personal.chart;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import tsi.personal.application.MetodosAleatorios;
import tsi.personal.atividades.AtividadeFisica;
/**
 * Classe responsavel por gerar data set para serem consumidos
 * pelos metodos da classe FabricaDeGraficos
 * @author tivin
 *
 */
public class DataSetGraficos {

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoDuracao(List<AtividadeFisica> laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int hr,minuto;
		double segundo;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			hr=0;
			minuto=0;
			segundo=0D;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					hr += Integer.parseInt(af.getDuracao().split(":")[0]);
					minuto += Integer.parseInt(af.getDuracao().split(":")[1]);

					StringBuilder seg = new StringBuilder("0.");
					seg.append(af.getDuracao().split(":")[2]);
					segundo += Double.parseDouble(seg.toString());
					System.out.println(hr);
					System.out.println(minuto);
					System.out.println(segundo);
				}
			}
			Double tempoTotal = new Double(hr*60);
			tempoTotal+=minuto;
			tempoTotal+=segundo;
			dataset.addValue(tempoTotal, tempoTotal, sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim graficoduração

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param tipo tipo de grafico a sser impresso 1 coluna 2 linha
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoDistancias(List<AtividadeFisica> laf, int tipo){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double distancia;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			distancia=0D;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0)
					distancia+=af.getDistancia();
			}
			if(tipo==1)
				dataset.addValue(distancia,distancia, sdf.format(calendar.getTime()));
			if(tipo==2)
				dataset.addValue(distancia, "Distancias Percorridas", sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim distanciaGrafico

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param tipo tipo de grafico a sser impresso 1 coluna 2 linha
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoKcal (List<AtividadeFisica> laf,int tipo){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Integer kCal;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			kCal=0;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0)
					kCal+=af.getkCal();
			}
			if(tipo==1)
				dataset.addValue(kCal, kCal, sdf.format(calendar.getTime()));
			if(tipo==2)
				dataset.addValue(kCal, "Kcal perdidas", sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim kCalGrafico

	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @param tipo tipo de grafico a sser impresso 1 coluna 2 linha
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoPassos(List<AtividadeFisica>laf, int tipo){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Long passos = 0l;
		/*for (Calendar calendar : lData) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("datas : "+sdf.format(calendar.getTime()));
		}*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			passos=0l;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					passos = Long.sum(passos, af.getPassos());
				}
			}
			if(tipo==1)
				dataset.addValue(passos, passos, sdf.format(calendar.getTime()));
			if(tipo==2)
				dataset.addValue(passos, "Passos",sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim passosGrafico
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoVelocidadeMedia(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double velMedia;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			velMedia=0d;
			List<AtividadeFisica> lafc = MetodosAleatorios.listaAtividadeComplexa(laf);
			for (AtividadeFisica afc : lafc) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(afc.getData().getTime()))==0)
					if(afc.getVelMedia()!=null)
						velMedia+=afc.getVelMedia();
			}
			dataset.addValue(velMedia, velMedia, sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim velocidadeMediaGrafico
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoRitmoMedio(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double ritmoMedio,segundos;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			ritmoMedio=0d;
			segundos=0d;
			List<AtividadeFisica> lafc = MetodosAleatorios.listaAtividadeComplexa(laf);
			for (AtividadeFisica afc : lafc) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(afc.getData().getTime()))==0)
					if(afc.getRitmoMedio()!=null){
						ritmoMedio+=Double.parseDouble(afc.getRitmoMedio().split("'")[0]);
						segundos+=Double.parseDouble(afc.getRitmoMedio().split("'")[1].substring(0, 1));
					}
			}
			if(segundos>60){
				ritmoMedio+=segundos/60;
				StringBuilder sb = new StringBuilder("0.");
				sb.append(segundos%60);
				ritmoMedio+=Double.parseDouble(sb.toString());
			}else
				ritmoMedio+=segundos;
			dataset.addValue(ritmoMedio, ritmoMedio, sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim ritmo mediografico
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoDistanciaMedia(List<AtividadeFisica>laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double distancia;
		int i=0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			distancia=0D;
			i=0;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					distancia+=af.getDistancia();
					i++;
				}
			}
			DecimalFormat df = new DecimalFormat("###,##0.00");
			distancia = Double.parseDouble(df.format(distancia/i).replace(".", "").replace(",", "."));
			dataset.addValue(distancia,distancia, sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim distanciamedia
	/**
	 * a partir de uma lista de atividade fisica atribui valores as um data set
	 * e o retorna esse data set para ser utilizado em um grafico
	 * @param laf lista de atividade fisica
	 * @return data set
	 */
	protected static DefaultCategoryDataset datasetGraficoMediaKcal (List<AtividadeFisica> laf){
		List<Calendar> lData = MetodosAleatorios.todasDatas(laf);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double kCal;
		int i=0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar calendar : lData) {
			kCal=0D;
			i=0;
			for (AtividadeFisica af : laf) {
				if(sdf.format(calendar.getTime()).compareToIgnoreCase(sdf.format(af.getData().getTime()))==0){
					kCal+=af.getkCal();
					i++;
				}
			}
			DecimalFormat df = new DecimalFormat("###,##0.00");
			kCal = Double.parseDouble(df.format(kCal/i).replace(".", "").replace(",", "."));
			dataset.addValue(kCal, kCal, sdf.format(calendar.getTime()));
		}
		return dataset;
	}//fim kCalGrafico

}
