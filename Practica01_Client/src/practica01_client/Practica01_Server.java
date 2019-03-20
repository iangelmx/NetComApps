/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica01_client;

/**
 *
 * @author neyer
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */

public class Practica01_Server {
    public static void main(String[] args) throws IOException {
        int num_env=0;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("server is running");
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }
        while(true){
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        
        try {
            
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }
        if(num_env==0){
            try {
            out = new FileOutputStream("C:\\Users\\neyer\\Documents\\ESCOM\\8vo Semestre\\Aplicaciones para Comunicaciones en Red\\P1\\out.txt");
            } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
            }
             byte[] bytes = new byte[32*1024];

            int count;
            int i=0;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                i++;
                System.out.println("linea "+i+". Cuenta servidor: "+count);
            }

            out.close();
            in.close();
            num_env++;
        }else{
            System.out.println("aquí se muere");
            socket.close();
            serverSocket.close();
            break;
        }
        num_env++;
        System.out.println(num_env);
       }
       
    }
       
}
