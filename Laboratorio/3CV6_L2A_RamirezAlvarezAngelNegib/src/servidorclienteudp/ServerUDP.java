package servidorclienteudp;
import java.net.*;

public class ServerUDP {
    public static void main(String[] args){
        try{
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado, esperando clientes...\n");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[2000],2000);
                s.receive(p);
                System.out.println("Datagrama recibido desde Cliente "+p.getAddress()+":"+p.getPort());
                String msj = new String(p.getData(),0,p.getLength());
                System.out.println("Con mensaje:"+ msj);
                
                /*Aquí retacha al cliente*/
                String mensaje = msj;
                byte[] b = mensaje.getBytes();
                String dst = "127.0.0.1";
                int pto = 2000;
                s.send(p);
                System.out.println("Se envió de retacho...");
                /*Fin retacho cliente*/
                
                
            }//for
            //s.close()
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}