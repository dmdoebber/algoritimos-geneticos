/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritimoGenetico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class Individuo implements Comparable<Individuo>{
    private List espacos;
    private List valores;
    private Double limiteEspacos;
    private Double notaAvaliacao;
    private Double espacoUsado;
    private int geracao;
    private List cromossomo;

    public Individuo(List espacos, List valores, Double limite) {
        this.espacos = espacos;
        this.valores = valores;
        this.limiteEspacos = limite;            
        this.notaAvaliacao = 0.0;
        this.geracao = 0;
        this.cromossomo = new ArrayList();
        
        for(int i  = 0; i < this.espacos.size(); i++)
            if(Math.random() < 0.5)
                this.cromossomo.add("0");
            else
                this.cromossomo.add("1");
    }
    
    
    public void avaliacao(){
        Double nota = 0.0;
        Double somaEspacos = 0.0;
        
        for(int i  = 0; i < this.cromossomo.size(); i++){
            if(this.cromossomo.get(i).equals("1")){
                nota += (Double) this.valores.get(i);
                somaEspacos += (Double) this.espacos.get(i);
            }
        }
        
        if(somaEspacos > this.limiteEspacos)
            nota = 1.0;
        
        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }
    
    public List crossover(Individuo ind){
        int corte = (int) (Math.random() * this.cromossomo.size());
        
        List filho1 = new ArrayList();
        filho1.addAll(ind.getCromossomo().subList(0, corte));
        filho1.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));
        
        List filho2 = new ArrayList();
        filho2.addAll(this.cromossomo.subList(0, corte));
        filho2.addAll(ind.getCromossomo().subList(corte, this.cromossomo.size()));
        
        List<Individuo> filhos = new ArrayList();
        filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspacos));
        filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspacos));
        
        filhos.get(0).setCromossomo(filho1);
        filhos.get(0).setGeracao(this.geracao + 1);
        
        filhos.get(1).setCromossomo(filho2);
        filhos.get(1).setGeracao(this.geracao + 1);
        
        return filhos;
    }
    
    public Individuo mutacao(Double taxaMutacao){
        
        for(int i = 0; i <  this.cromossomo.size(); i++)
           if(Math.random() < taxaMutacao)
               if(this.cromossomo.get(i).equals("1"))
                   this.cromossomo.set(i, "0");
               else
                   this.cromossomo.set(i, "1");
        
        return this;
    }
    
    @Override
    public int compareTo(Individuo ind) {
        if(this.notaAvaliacao > ind.getNotaAvaliacao())
            return -1;
        if(this.notaAvaliacao < ind.getNotaAvaliacao())
            return 1;
        return 0;
    }

    public List getEspacos() {
        return espacos;
    }

    public void setEspacos(List espacos) {
        this.espacos = espacos;
    }

    public List getValores() {
        return valores;
    }

    public void setValores(List valores) {
        this.valores = valores;
    }

    public Double getLimite() {
        return limiteEspacos;
    }

    public void setLimite(Double limite) {
        this.limiteEspacos = limite;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double nota) {
        this.notaAvaliacao = nota;
    }

    public int getGeracao() {
        return geracao;
    }

    public Double getEspacoUsado() {
        return espacoUsado;
    }

    public void setEspacoUsado(Double espacoUsado) {
        this.espacoUsado = espacoUsado;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public List getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List cromossomo) {
        this.cromossomo = cromossomo;
    }
}
