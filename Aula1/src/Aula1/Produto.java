/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1;

/**
 *
 * @author Rafael Marques <marquesmiranda.r@gmail.com>
 */
public class Produto {
    
    private String nome;
    private String path;
    private Double preco;

    public Produto() {
    }

    public Produto(String nome, String path, Double preco) {
        this.nome = nome;
        this.path = path;
        this.preco = preco;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", preço=" + preco + '}';
    }

    
    
    
    
    
}
