/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica02_carritocompra;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;

public class Servidor {
    public static void iniciaServidor(int puerto, int buffer) throws IOException{
        ServerSocket serverSocket = null;
        boolean flag = false;
        String filePathInventario = "./inventario/invent.json";

        serverSocket = new ServerSocket(puerto);
        System.out.println("Se ha iniciado el servidor...");
        while(true){
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            int count;
            if(flag == false){
                socket = serverSocket.accept();
                File file=new File( filePathInventario );

                long length = file.length();
                byte[] bytes = new byte[buffer * 1024];

                in = new FileInputStream(file);
                out = socket.getOutputStream();
                
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
                flag = true;
            }
            /*Hasta aquí termina la primera parte de envío del catálogo*/
            
            /*Aquí hacia abajo viene la parte en que se recibe la respuesta del cliente de compra*/
            in = socket.getInputStream();
            out = new FileOutputStream("src\\inventario\\recibidoDeCliente.json");
            byte[] bytes = new byte[buffer*1024];
            int i=0;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                //i++;
                //System.out.println("linea "+i+". Cuenta servidor: "+count);
            }
            
            FileInputStream fstream = new FileInputStream("src\\objetos\\recibidoDeCliente.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line;
            
            Gson gson= new Gson();
            Item[] items=new Item[200];
            int a = 0;
            while ((line = br.readLine()) != null) {
               items[a]= gson.fromJson(line,Item.class);
               System.out.println(line);
               a+=1;
            }
            
            
        }
        serverSocket.close();
    }
    
    
    public static void main(String[] args) throws IOException {
        iniciaServidor(3060, 30);
    }
}
