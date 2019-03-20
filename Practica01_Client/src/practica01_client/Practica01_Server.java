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

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */

public class Practica01_Server {
    
    
    public static void run_serve() throws IOException{
        ArrayList<ArrayList<String>> files = new ArrayList<ArrayList<String>>();       
        int num_env=0;
        int contador=0;
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
            out = new FileOutputStream("src\\uploads\\datosEnvio.txt");
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
            socket.close();
            FileInputStream fstream = new FileInputStream("src\\uploads\\datosEnvio.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String line;
                while ((line = br.readLine()) != null) {
                   files.add(new ArrayList(Arrays.asList(line.split("\\|"))));
                }
                System.out.println(files.toString());
               
		
        }else{
            
            if(contador<files.size()){
                try {   
                         
                        out = new FileOutputStream("src\\uploads\\"+files.get(contador).get(0));
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
                        contador++;
                        out.close();
                        in.close();
                        socket.close();
                       
            }else{
                break;
            }
            
            
        }
        System.out.println("Recibiendo archivo: "+num_env);
        num_env++;
        
       }
    serverSocket.close();
       
    
    }
    public static void main(String[] args) throws IOException {
        
        run_serve();
    }
       
}
