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
    
    /*
    public static Item[] getInventario () throws IOException{
        FileInputStream fstream = new FileInputStream("src\\inventario\\inventario.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;

        Gson gson= new Gson();
        Item[] items=new Item[10];
        int a = 0;
        while ((line = br.readLine()) != null) {
           items[a]= gson.fromJson(line,Item.class);
           System.out.println(line);
           a+=1;
        }
        return items;
    }
    */
    public static void iniciaServidor(int puerto, int buffer) throws IOException{
        ServerSocket serverSocket = null;
        boolean flag = false;
        
        Gson json= new Gson();
        
        serverSocket = new ServerSocket(puerto);
        System.out.println("Se ha iniciado el servidor...");
        while(true){
            Socket socket = null;
            InputStream inFile = null,inSocket=null;
            OutputStream outSocket = null,outFile=null;
            int count;
            if(flag == false){
                System.out.println("Llegó aquí");
                socket = serverSocket.accept();
                System.out.println("Aceptó una conexión");
                
                
                byte[] bytes = new byte[buffer * 1024];
                System.out.println(json.toJson(getInventario()));
                inFile = new ByteArrayInputStream(json.toJson(getInventario()).getBytes());
                

                outSocket = socket.getOutputStream();
                
                while ((count = inFile.read(bytes)) > 0) {
                    outSocket.write(bytes, 0, count);
                }
                //outSocket.flush();
                socket.close();
                System.out.println("Se supone que lo envió");
                flag=true;
            }
            else{
              
                /*Hasta aquí termina la primera parte de envío del catálogo*/
                // System.out.println("Entro al else");
                /*Aquí hacia abajo viene la parte en que se recibe la respuesta del cliente de compra*/
                    try{
                    socket = serverSocket.accept();  
                    inSocket = socket.getInputStream();
                    System.out.println("Trató de obtener un dato de entrada");
                    outFile = new FileOutputStream("src\\inventario\\recibidoDeCliente.json");
                    byte[] bytes = new byte[buffer*1024];
                    int i=0;
                    String msg;
                    System.out.println("Esperará archivo");
                    while ((count = inSocket.read(bytes)) > 0) {
                        outFile.write(bytes, 0, count);
                        i++;
                        System.out.println("linea "+i+". Cuenta servidor: "+count);
                    }
                    
                    System.out.println("Salió del while");
                    //setInventario("src\\inventario\\recibidoDeCliente.json");
                    flag=false;
                    }
                    catch(Exception ex){
                        //System.out.println("Excepción Else Servidor: "+ex);
                    }
                }
                
                

                /*FileInputStream fstream = new FileInputStream("src\\objetos\\recibidoDeCliente.json");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String line;

                Gson gson= new Gson();
                Item[] items=new Item[11];
                int a = 0;
                while ((line = br.readLine()) != null) {
                   items[a]= gson.fromJson(line,Item.class);
                   System.out.println(line);
                   a+=1;
                }*/

                //Item[] inventario = getInventario();
            }
            
            
    }
        
   public static void setInventario(String lista) throws IOException{
        InputStream inFile=null;
        OutputStream outFile=null;
        int i=0;
        Item[] inventarioActual =getInventario();
        Item[] listaCliente=null;
        inFile=new FileInputStream(lista);
        BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
        String line;
        Gson gson= new Gson();
        
        while ((line = br.readLine()) != null) {
           listaCliente = gson.fromJson(line,Item[].class);
        }
        
        for(Item productoC:listaCliente){
            for(Item productoS: inventarioActual){
            
                if(productoC.sku.equals(productoS.sku)){
                    productoS.exis-=productoC.exis;
                    inventarioActual[i]=productoS;
                    
                }
                i++;
            }
            
            i=0;
        
        }
        
        outFile = new FileOutputStream("src\\inventario\\inventario.json");
        byte[] bytes = new byte[30*1024];
        int count=0;
        inFile=new ByteArrayInputStream(gson.toJson(inventarioActual).getBytes());
        while ((count = inFile.read(bytes)) > 0) {
                        outFile.write(bytes, 0, count);   
                        System.out.println("linea "+i+". Cuenta servidor: "+count);
                    }
    
    }
    public static Item[] getInventario () throws IOException{
        FileInputStream fstream = new FileInputStream("src\\inventario\\inventario.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;

        Gson gson= new Gson();
        Item[] items=new Item[10];
        int a = 0;
        while ((line = br.readLine()) != null) {
           items = gson.fromJson(line,Item[].class);
           System.out.println(line);
           a+=1;
        }
        return items;
    }
    
    
    public static void main(String[] args) throws IOException {
        Item[] items = { new Item("1ASDF", "Hot Wheels", 3, 10), new Item("2ASDF", "Tomates", 4, 32) };
        Gson json = new Gson();
        String filePathInventario = "src\\inventario\\inventario.json";
        String jsonStr = json.toJson(items);
        OutputStream outFile=null;
         File file =new File(filePathInventario);
                outFile = new FileOutputStream(file);
                outFile.write(jsonStr.getBytes());
        iniciaServidor(3060, 300);
    }
}
