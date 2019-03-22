/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;



import serializacion.persona;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author neyer
 */
public class cliente {
     
     
    public static void run_cliente() throws IOException{
        java.sql.Timestamp date = java.sql.Timestamp.valueOf("1956-12-05 09:43:00.0");
        persona per1= new persona("simon el gran varon","elgr561205hfdlnl05",date,'M',18,1.56);
        
        Gson gson= new Gson();
        String JSON = gson.toJson(per1);
        
        System.out.println(per1.toString());
        System.out.println(JSON);
        

        send_file_server(JSON);
        
    }
    
    public static void send_file_server(String datos) throws IOException  {
       
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        String host = "localhost";

        try{
        socket = new Socket(host, 3060);
        }catch(IOException ex){
                System.out.println("No se pudo establecer la conexion"+ ex);
        }
        
        File file=new File("src\\files\\persona.json");
        FileWriter fw = new FileWriter(file);
        PrintWriter escribir = null;
            if(file.exists()) {
                escribir = new PrintWriter(fw);
                escribir.write(datos);
            }
        escribir.close();
        long length = file.length();
        byte[] bytes = new byte[50 * 1024];
        try{
             in = new FileInputStream(file);
        }catch(FileNotFoundException ex){
            System.out.println("Archivo no encontrado"+ ex);
        }
         try{
             out = socket.getOutputStream();
        }catch(IOException ex){
            System.out.println("Socket no encontrado"+ ex);
        }
        

        int count;
        int a = 0;
        double progresoEnvio = 0;
        long acum = 0;
        while ((count = in.read(bytes)) > 0) {
            a++;
            acum += count;
            out.write(bytes, 0, count);
            //System.out.println("Cuenta Cli:"+count+" lin: "+a);
            progresoEnvio = (100 * acum) / length;
            System.out.println("Progreso: "+progresoEnvio);
        }
        out.close();
        in.close();
        socket.close();
    }
public static void main(String[] args) throws IOException {
     run_cliente();
    }        
    
    
}
