/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritimoGenetico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author danie
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Produto> listaProdutos = new File("produtos.txt").load();
        
        List espacos = new ArrayList();
        List valores = new ArrayList();
        List nomes = new ArrayList();
        
        for(Produto p : listaProdutos){
            espacos.add(p.getTamanho());
            valores.add(p.getValor());
            nomes.add(p.getNome());
        }
        
        Double limite = 3.0;
        int tamanhoPopulacao = 20;
        Double taxaMutacao = 0.05;
        int numGeracoes = 100;
        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanhoPopulacao);
        
        List resultado = ag.resolver(taxaMutacao, numGeracoes, espacos, valores, limite);
        
        for(int i = 0; i < listaProdutos.size(); i++)
            if(resultado.get(i).equals(("1")))
                System.out.println("Nome: "+listaProdutos.get(i).getNome()
                                + "Preço: R$"+listaProdutos.get(i).getValor());
        
        Grafico g = new Grafico("Algoritimo Genético", "Evolução das soluções", ag.getMelhoresCromossomos());
        g.pack();
        
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);
    }
}
