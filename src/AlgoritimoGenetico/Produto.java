/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritimoGenetico;

/**
 *
 * @author danie
 */
public class Produto {
    private String nome;
    private Double tamanho;
    private Double valor;

    public Produto(String nome, Double tamanho, Double valor) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
