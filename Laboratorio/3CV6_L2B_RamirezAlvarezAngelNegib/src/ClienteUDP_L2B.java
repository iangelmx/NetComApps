import java.net.*;
import java.io.*;

public class ClienteUDP_L2B {
    public static void main(String[] args){
        try{
            DatagramSocket cl = new DatagramSocket();
            System.out.println("Cliente iniciado. Se calculará el RFC\nEscriba su primer y segundo nombre:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = "";

            mensaje += br.readLine();
            System.out.println("Escriba su apellido Paterno:");
            mensaje += ";"+br.readLine();
            System.out.println("Escriba su apellido Materno:");
            mensaje += ";"+br.readLine();

            System.out.println("Ahora sólo introduzca su fecha de Nacimiento en formato YYYY-MM-DD:");
            mensaje += ";"+br.readLine();

            byte[] b = mensaje.getBytes();
            String dst = "127.0.0.1";
            int pto = 2000;
            DatagramPacket p = new DatagramPacket(b,b.length,InetAddress.getByName(dst),pto);
            cl.send(p);
            
            /* Aquí se empieza recepción*/
            cl.receive(p);
            System.out.println("----------\nRFC recibido de Servidor "+p.getAddress()+":"+p.getPort());
            String msj = new String(p.getData(),0,p.getLength());
            System.out.println("Resultado:>"+ msj+"<");
            /*Se termina recepción*/
            
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}
