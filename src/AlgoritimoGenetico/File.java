/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritimoGenetico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class File {
    private final RandomAccessFile file; 
    private final List<Produto> list;

    public File(String nameFile) throws FileNotFoundException {
        this.file = new RandomAccessFile(nameFile, "rwd");
        this.list = new ArrayList();
    }
    
    public List<Produto> load() throws IOException{
        String txt;
        String split[];
        String nome;
        Double tamanho;
        Double preco;
        
        while((txt = file.readLine()) != null){
            split = txt.split(" ");
            
            nome = split[0];
            tamanho = Double.parseDouble(split[1]);
            preco = Double.parseDouble(split[2]);
            
            list.add(new Produto(nome, tamanho, preco));
        }
        
        return list;
    }
    
    
    
    
}
