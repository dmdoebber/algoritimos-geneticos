/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritimoGenetico;

import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author danie
 */
public class Grafico extends ApplicationFrame{
    private List<Individuo> melhoresCromossomos;
    
    public Grafico(String tituloJanela, String tituloGrafico, List melhores){
        super(tituloJanela);
        this.melhoresCromossomos = melhores;
        JFreeChart graficoLinha = ChartFactory.createLineChart(tituloGrafico, "Geração", "Valor",
                carregarDados(), PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel janelaGrafico = new ChartPanel(graficoLinha);
        janelaGrafico.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(janelaGrafico);
    }
    
    private DefaultCategoryDataset carregarDados(){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
        
        for(int i = 0; i < melhoresCromossomos.size(); i++)
            dados.addValue(melhoresCromossomos.get(i).getNotaAvaliacao(), "Melhor Solução", ""+i);
        
        return dados;

    }
}