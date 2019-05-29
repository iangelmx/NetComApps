/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadpoolejemplo;

public class Task implements Runnable{
    private int limit;
    private String nombre;
    public Task(String name){
        this.nombre = name;
        limit = 50;
    }
    public void run(){

        for(int a = 0; a<= this.limit; a++){
            System.out.printf("El Hilo:"+this.nombre+" va contando: %d\n", a);
        }
        
        System.out.println("El hilo: "+this.nombre+", Finalizó cuenta...");
    }
}