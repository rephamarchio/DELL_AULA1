/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rafael Marques <marquesmiranda.r@gmail.com>
 */
public class Principal {

    public static String DataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) {

        //instanciando dois objetos do tipo Cliente
        Cliente cliente1 = new Cliente("Rafael");
        Cliente cliente2 = new Cliente("José");

        //instanciando seis objetos do tipo Produto
        Produto produto1 = new Produto("agua", "D/2020/COMPUTACAO/DELL/Aula1/src/Produtos/agua", 2.00);
        Produto produto2 = new Produto("cafe", "D/2020/COMPUTACAO/DELL/Aula1/src/Produtos/cafe", 6.50);
        Produto produto3 = new Produto("cuzcuz", "D/2020/COMPUTACAO/DELL/Aula1/src/Produtos/cuzcuz", 4.90);
        Produto produto4 = new Produto("abacaxi", "D/2020/COMPUTACAO/DELL/Aula1/src/Produtos/abacaxi", 2.50);
        Produto produto5 = new Produto("arroz", "D/2020/COMPUTACAO/DELL/Aula1/src/Produtos/arroz", 8.10);
        Produto produto6 = new Produto("tomate", "D/2020/COMPUTACAO/DELL/Aula1/src/Produtos/tomate", 5.55);

       /// System.out.print(DataAtual());
        
        //compras cliente 1
        ArrayList<Produto> CarrinhoDeCompras1 = new ArrayList<>();
        CarrinhoDeCompras1.add(produto1);
        CarrinhoDeCompras1.add(produto2);
        CarrinhoDeCompras1.add(produto3);

        ArrayList<Produto> CarrinhoDeCompras2 = new ArrayList<>();
        CarrinhoDeCompras2.add(produto4);
        CarrinhoDeCompras2.add(produto5);
        CarrinhoDeCompras2.add(produto6);

        RegistroDePagamento reg1 = new RegistroDePagamento(CarrinhoDeCompras1, DataAtual(), cliente1);
        RegistroDePagamento reg2 = new RegistroDePagamento(CarrinhoDeCompras2, DataAtual(), cliente2);
        
        System.out.println(reg1);
        System.out.println(reg2);
    }

}
