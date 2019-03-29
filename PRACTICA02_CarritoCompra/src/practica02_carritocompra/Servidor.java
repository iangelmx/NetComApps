/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica02_carritocompra;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;

class Item{
    private String sku = "";
    private String nombre = "";
    private int exis = 0;
    private int precio = 0;
    public Item(String sku, String nombre, int exis, int precio){
        this.sku = sku;
        this.nombre = sku;
        this.exis = exis;
        this.precio = precio;
    }
    @Override
    public String toString(){
        return "[ "+this.sku+" , "+this.nombre+" , Exis: "+this.exis+", Precio:"+this.precio+" ]";
    }
}

public class Servidor {
    
    public static File writeToFile(String pathFile, String toWrite) throws IOException{
        File file=new File( pathFile );
        FileWriter fw = new FileWriter(file);
        PrintWriter escribir = null;
        escribir = new PrintWriter(fw);
        escribir.write(toWrite);
        escribir.close();
        return file;
    }
    
    public static void sendFileToServer(String datos, String host, int puerto, int buffer) throws IOException  {
        int count;
        int a = 0;
        double progresoEnvio = 0;
        
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try{
            socket = new Socket(host, puerto);
        }catch(IOException ex){
            System.out.println("Conection failed: "+ ex);
        }
        
        File file = writeToFile("src\\objetos\\StoC.json", datos);
        
        long length = file.length();
        byte[] bytes = new byte[buffer * 1024];
        try{
            in = new FileInputStream(file);
        }catch(FileNotFoundException ex){
            System.out.println("ERROR 404: "+ ex);
        }
        try{
            out = socket.getOutputStream();
        }catch(IOException ex){
            System.out.println("Exception Socket: "+ ex);
        }
        
        long acum = 0;
        while ((count = in.read(bytes)) > 0) {
            a++;
            acum += count;
            out.write(bytes, 0, count);
            progresoEnvio = (100 * acum) / length;
            System.out.println("Progreso: "+progresoEnvio);
        }
        out.close();
        in.close();
        socket.close();
    }
    
    public static void iniciaServidor(int puerto, int buffer) throws IOException{
        
        Item invent [] = {
            new Item("1AB", "Paleta", 3, 12),
            new Item("2CD", "Chocolate", 1, 5)
        };
        
        String serializado = gson.toJson(invent);
       
        
        ServerSocket serverSocket = null;
        Gson gson=new Gson();
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Se ha iniciado el servidor...");
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
        
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        
        while(true){
            try {
            socket = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("No se pudo aceptar conexión: "+ex);
            }
            
            /*Aquí se envía el catálogo al cliente*/
            
            //Sending the response back to the client.
            OutputStream out2 = socket.getOutputStream();
            File myFile=new File( FILE_TO_RECEIVED );
            FileInputStream fis=new FileInputStream(myFile);
            byte [] mybytearray1  = new byte [(int)myFile.length()];


            int len ;
            while ((len = fis.read(mybytearray1)) >= 0) {
                out.write(len);
                System.out.println("Message sent to the client is "+ current);
                out.flush();
                   //fis.close();
            }  
            
            /*Se termina de enviar el catálogo*/
            
            try {
                in = socket.getInputStream();
            } catch (IOException ex) {
                System.out.println("Error con socket In: " +ex);
            }
            try {
                out = new FileOutputStream("src\\objetos\\recieved.json");
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
            }
             byte[] bytes = new byte[buffer*1024];
            int count;
            int i=0;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                i++;
                System.out.println("linea "+i+". Cuenta servidor: "+count);
            }
            
            System.out.println("Message received from client is "+current);
                     
        }        
        
        out.close();
        in.close();
        socket.close();
        FileInputStream fstream = new FileInputStream("src\\objetos\\recieved.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        while ((line = br.readLine()) != null) {
           per1= gson.fromJson(line,Persona.class);
           System.out.println(line);
           System.out.println(per1.toString());
        }
        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        iniciaServidor(3060, 0);
    }
}
