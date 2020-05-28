/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.Y;

/**
 *
 * @author Rafael Maeques Miranda
 */
public class Contador extends Thread{
    
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contador(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void run(){
        for (int i = 1; i < 50; i++) {
            System.out.println("Thread "+this.getName()+" contando: "+i);
            try {
                sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
     public static void main(String[] args) {
        // TODO code application logic here
        
         for (int i = 1; i < 50; i++) {
             Contador c = new Contador(String.valueOf(i));
             c.start();
         }
    }
}
