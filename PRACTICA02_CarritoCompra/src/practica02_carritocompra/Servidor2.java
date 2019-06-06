/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica02_carritocompra;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author iAngelMx
 */
public class Servidor2 {
    
    
    public static void iniciaServidor(int puerto, int buffer) throws IOException{
        ServerSocket serverSocket = null;
        boolean flag = false;
        
        Gson json= new Gson();
        
        serverSocket = new ServerSocket(puerto);
        System.out.println("Se ha iniciado el servidor...");
        int idSession = 0;
        while(true){
            Socket socket = null;
            
            System.out.println("Llegó aquí");
            socket = serverSocket.accept();
            System.out.println("Aceptó una conexión");
            ((ServidorHilo) new ServidorHilo(socket, idSession, buffer)).start();
            idSession++;
        }
    }
    
    
    public static void main(String args[]){
        Item[] items = { new Item("1ASDF", "Hot Wheels", 3, 10), new Item("2ASDF", "Tomates", 4, 32) };
        Gson json = new Gson();
        String filePathInventario = "src\\inventario\\inventario.json";
        String jsonStr = json.toJson(items);
        OutputStream outFile=null;
        File file =new File(filePathInventario);
        try{
            outFile = new FileOutputStream(file);
            outFile.write(jsonStr.getBytes());
        }
        catch(Exception ex){
            System.out.println("Hubo un problema al preIniciar: "+ex);
        }
        try{
            iniciaServidor(3060, 300);
        }catch(Exception ex){
            System.out.println("Hubo un problema al iniciaServidor(3060, 300): "+ex);
        }
        
    }
    
}
