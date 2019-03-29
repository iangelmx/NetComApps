package servidorclienteudp;
import java.net.*;
import java.io.*;

public class ClienteUDP {
    public static void main(String[] args){
        try{
            DatagramSocket cl = new DatagramSocket();
            System.out.println("Cliente iniciado, escriba un mensaje a enviar:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            String dst = "127.0.0.1";
            int pto = 2000;
            DatagramPacket p = new DatagramPacket(b,b.length,InetAddress.getByName(dst),pto);
            cl.send(p);
            
            /* Aquí se empieza recepción*/
            cl.receive(p);
            System.out.println("----------\nDatagrama recibido de Servidor "+p.getAddress()+":"+p.getPort());
            String msj = new String(p.getData(),0,p.getLength());
            System.out.println("Con el mensaje:>"+ msj+"<");
            /*Se termina recepción*/
            
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}
