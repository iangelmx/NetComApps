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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iAngelMx
 */
public class ServidorHilo extends Thread{
    
    private Socket socket;
    private int idSessio;
    DataOutputStream dos;
    DataInputStream dis;
    InputStream inFile = null,inSocket=null;
    OutputStream outSocket = null,outFile=null;
    private Gson json;
    private int buffer;
    ServerSocket serverSocket = null;
    private boolean flag = false;
    
    public ServidorHilo(Socket socket, int id, int buffer) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Hubo una excepción en __Constructor__: "+ex);
        }
        this.json= new Gson();
        this.buffer = buffer;
        
        try {
            sendInventario();
        } catch (IOException ex) {
            System.out.println("Hubo un problema al enviar el inventario");
        }
        
    }

    ServidorHilo(Socket socket, int idSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Hubo una excepción en desconectar: "+ex);
        }
    }
    
    public Item[] getInventario () throws IOException{
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
    
    public void setInventario(String lista) throws IOException{
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
    
    public void sendInventario() throws IOException{
        int count;
        byte[] bytes = new byte[this.buffer * 1024];
        System.out.println(this.json.toJson(getInventario()));
        inFile = new ByteArrayInputStream(this.json.toJson(getInventario()).getBytes());


        outSocket = socket.getOutputStream();
        while ((count = inFile.read(bytes)) > 0) {
            outSocket.write(bytes, 0, count);
        }
        System.out.println("Se supone que envió inventario");
        outSocket.close();
        //inFile.reset();
        flag=true;
    }
   
    
    @Override
    public void run(){
        int count;
        while(true){
            System.out.println("Está en whileTrue");
            try{
                Thread.sleep(3000);
            }catch(Exception ex){
                System.out.println("Excepción del sleep");
            }
            if( flag == true){
                try{
                    inSocket = this.socket.getInputStream();
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
                    setInventario("src\\inventario\\recibidoDeCliente.json");
                    flag=false;
                }
                catch(Exception ex){
                    System.out.println("Excepción en Servidor: "+ex);
                }
            }
        }
    }
    
}
