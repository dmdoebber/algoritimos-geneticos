/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritimoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author danie
 */
public class AlgoritmoGenetico {
    private int tamPopulacao;
    private List<Individuo> populacao;
    private int geracao;
    private Individuo melhorSolucao;
    
    private List<Individuo> melhoresCromossomos;

    public AlgoritmoGenetico(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        this.populacao = new ArrayList();
        this.melhoresCromossomos = new ArrayList();
    }
    
    public void inicializaPopulacao(List espacos, List valores, Double limiteEspacos){
        for(int i = 0; i < this.tamPopulacao; i++)
            populacao.add(new Individuo(espacos, valores, limiteEspacos));
        
        this.melhorSolucao = this.populacao.get(0);
    }
    
    public void ordenaPopulacao(){
        Collections.sort(this.populacao);
    }
    
    public void melhorIndividuo(Individuo ind){
        if(ind.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao())
            this.melhorSolucao = ind;
    }
    
    public Double somaAvaliacoes(){
        Double soma = 0.0;
        
        for(Individuo ind : this.populacao){
            soma += ind.getNotaAvaliacao();
        }
        
        return soma;
    }
    
    public int selecionaPai(Double somaAvaliacao){
        Double valorSorteado = Math.random() * somaAvaliacao;
        Double soma = 0.0;
        
        int pai = -1;
        int i = 0;
        
        while(i < this.populacao.size() && soma < valorSorteado){
            soma += this.populacao.get(i).getNotaAvaliacao();
            pai ++;
            i++;
        }
        
        return pai;  
    }
    
    public void visualizaGeracao(){
        Individuo melhor = this.populacao.get(0);
        this.melhoresCromossomos.add(melhor);
        System.out.println("Geracao: " + melhor.getGeracao() 
                        + " Valor: " + melhor.getNotaAvaliacao()
                        + " Espaço: " + melhor.getEspacoUsado()
                        + " Cromossomo: " + melhor.getCromossomo());
    }
    
    public List resolver(Double taxaMutacao, int numGeracoes, List espacos, List valores, Double limiteEspacos){
        
        this.inicializaPopulacao(espacos, valores, limiteEspacos);
        
        for(Individuo ind : this.populacao)
            ind.avaliacao();
        
        this.ordenaPopulacao();
        this.visualizaGeracao();
        
        for(int geracao = 0; geracao < numGeracoes; geracao++){
            Double somaAvalicao = this.somaAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList();
            
            for(int i = 0; i < this.populacao.size() / 2; i++){
                int pai1 = this.selecionaPai(somaAvalicao);
                int pai2 = this.selecionaPai(somaAvalicao);
                
                List<Individuo> filhos = this.populacao.get(pai1).crossover(this.populacao.get(pai2));
                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));
            }
            
            this.setPopulacao(novaPopulacao);
            
            for(Individuo ind : this.populacao)
                ind.avaliacao();
            
            this.ordenaPopulacao();
            this.visualizaGeracao();
            
            Individuo melhor  = this.populacao.get(0);
            this.melhorIndividuo(melhor);
        }
        
        System.out.println("Melhor Solução: " 
                + "  Geração: " + this.melhorSolucao.getGeracao()
                + "  Valor: "+ this.melhorSolucao.getNotaAvaliacao()
                + "  Espaço Utilizado: " + this.melhorSolucao.getEspacoUsado()
                + "  Cromossomo: " + this.melhorSolucao.getCromossomo());
        
        return this.melhorSolucao.getCromossomo();
    }

    public List<Individuo> getMelhoresCromossomos() {
        return melhoresCromossomos;
    }

    public void setMelhoresCromossomos(List<Individuo> melhoresCromossomos) {
        this.melhoresCromossomos = melhoresCromossomos;
    }
    
    public int getTamPopulacao() {
        return tamPopulacao;
    }

    public void setTamPopulacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
    }

    public List<Individuo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }
    
    
    
    
}
