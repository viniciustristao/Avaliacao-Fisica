package tsi.personal.chart;
import java.util.List;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tsi.personal.atividades.AtividadeFisica;
import tsi.personal.relatorios.Relatorio;

/**
 * Classe com metodos responsaveis por gerar graficos
 * apartir de uma lista de exercios consumindo dados
 * gerados pela classe DataSetGraficos
 * @author tivin
 *
 */
public class FabricaDeGraficos {
	/**
	 * Cria uma tela com elementos GUI para exibição do grafico de dados
	 * e um relatorio de dados abaixo
	 * @param nodeGrafico Swing Node contendo o node do grafico a plotado na tela
	 * @param vbDados VBox com os dados do relatorio a ser plotado na tela
	 * @param nomeEx
	 */
	private static void gerarTelaGrafico(SwingNode nodeGrafico,VBox vbDados,String nomeEx){
		VBox vBox = new VBox();
		try {
			Stage primaryStage = new Stage();
			primaryStage.setTitle(nomeEx);
			AnchorPane root = new AnchorPane();
			vBox.getChildren().addAll(nodeGrafico,vbDados);
			root.getChildren().add(vBox);
			Scene scene = new Scene(root,800,500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * gera um grafico de duração de exercicios
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de execicio
	 */
	public static void graficoDuracao(List<AtividadeFisica>laf,String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoDuracao(laf);

		SwingNode node = new SwingNode();
		node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Duração de Exercicios", "Datas", "Minutos",
				dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}

	/**
	 * gera um grafico com duração de exercicios
	 * @param laf lista de atividade fisica
	 * @param tipo 1 grafico coluna tipo 2 grafico linha
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoDistancia(List<AtividadeFisica>laf, int tipo, String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoDistancias(laf,tipo);

		SwingNode node = new SwingNode();
		if(tipo==1)
			node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Distancia percorrida", "Datas", "Km",
					dataset,PlotOrientation.VERTICAL, true, true, false)));
		if(tipo==2)
			node.setContent(new ChartPanel(ChartFactory.createLineChart("Distancia percorrida", "Datas", "Km",
					dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}

	/**
	 * gera um grafico com quantidade de calorias perdidas
	 * @param laf lista de atividade fisica
	 * @param tipo 1 grafico coluna tipo 2 grafico linha
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoKcal(List<AtividadeFisica>laf, int tipo, String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoKcal(laf,tipo);

		SwingNode node = new SwingNode();
		if(tipo==1)
			node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Calorias perdidas", "Datas", "Kcal",
					dataset,PlotOrientation.VERTICAL, true, true, false)));
		if(tipo==2)
			node.setContent(new ChartPanel(ChartFactory.createLineChart("Calorias perdidas", "Datas", "Kcal",
					dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}
	/**
	 * gera um grafico com quantidade de passos dados
	 * @param laf lista de atividade fisica
	 * @param tipo 1 grafico coluna tipo 2 grafico linha
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoPassos(List<AtividadeFisica>laf, int tipo, String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoPassos(laf,tipo);

		SwingNode nodeGrafico = new SwingNode();
		if(tipo==1)
			nodeGrafico.setContent(new ChartPanel(ChartFactory.createBarChart3D("Passos Dados", "Datas", "Passos",
					dataset,PlotOrientation.VERTICAL, true, true, false)));
		if(tipo==2)
			nodeGrafico.setContent(new ChartPanel(ChartFactory.createLineChart("Passos Dados", "Datas", "Passos",
					dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(nodeGrafico, Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}
	/**
	 * gera um grafico com velociade media
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoVelMedia(List<AtividadeFisica>laf,String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoVelocidadeMedia(laf);

		SwingNode node = new SwingNode();
		node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Velocidade Media", "Datas", "Km/h",
				dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}

	/**
	 * gera um grafico com ritmo medio
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoRitmoMedio(List<AtividadeFisica>laf, String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoRitmoMedio(laf);

		SwingNode node = new SwingNode();
		node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Ritmo Medio", "Datas", "minutos",
				dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf),nomeEx);
	}
	/**
	 * gera um grafico com media de calorias perdidas
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoMediaKcalPerdidas(List<AtividadeFisica> laf, String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoMediaKcal(laf);

		SwingNode node = new SwingNode();
		node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Media de calorias perdidas", "Datas", "Kcal",
				dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}
	/**
	 * gera um grafico com media de distancia percorrida
	 * @param laf lista de atividade fisica
	 * @param nomeEx tipo de exercicio
	 */
	public static void graficoMediaDistancia(List<AtividadeFisica>laf, String nomeEx) {
		DefaultCategoryDataset dataset = DataSetGraficos.datasetGraficoDistanciaMedia(laf);
		SwingNode node = new SwingNode();
		node.setContent(new ChartPanel(ChartFactory.createBarChart3D("Distancia media percorrida", "Datas", "Km",
				dataset,PlotOrientation.VERTICAL, true, true, false)));
		gerarTelaGrafico(node,Relatorio.relatorioDadosDeExercicios(laf), nomeEx);
	}


}