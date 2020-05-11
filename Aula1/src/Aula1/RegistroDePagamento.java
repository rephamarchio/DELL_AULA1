/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rafael Marques <marquesmiranda.r@gmail.com>
 */
public class RegistroDePagamento {
    
    private ArrayList<Produto> produtos;
    private String data;
    private Cliente cliente;

    public RegistroDePagamento(ArrayList<Produto> produtos, String  data, Cliente cliente) {
        this.produtos = produtos;
        this.data = data;
        this.cliente = cliente;
    }
    
    public  RegistroDePagamento(){}

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String  getData() {
        return data;
    }

    public void setData(String  data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "RegistroDePagamento{" + "\n cliente=" +  cliente.getNome() +"\n data=" + data + "\n produtos=" + produtos +"\n" + '}';
    }

   
    
    
    
    
}
