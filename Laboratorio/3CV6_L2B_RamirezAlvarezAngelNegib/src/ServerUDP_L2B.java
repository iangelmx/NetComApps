import java.net.*;

public class ServerUDP_L2B {

    public static String RFCMaker(String datos){
        String data[] = datos.split(";");
        String rfc = "";
        //System.out.println("Data:"+data);
        String fechaNac[] = data[3].split("-");
        rfc = String.valueOf(data[1].charAt(0)) + String.valueOf(data[1].charAt(1));
        rfc += String.valueOf(data[2].charAt(0));
        rfc += String.valueOf(data[0].charAt(0));
        rfc += String.valueOf(fechaNac[0].charAt(2)) + String.valueOf(fechaNac[0].charAt(3));
        rfc += fechaNac[1];
        rfc += fechaNac[2];
        return rfc.toUpperCase();
    }

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

                String rfcUser = RFCMaker(msj);
                
                System.out.println("El RFC Generado: "+rfcUser);

                /*Aquí retacha al cliente*/
                String mensaje = rfcUser;
                System.out.println("Msg to send: "+mensaje);
                byte[] b = mensaje.getBytes();
                String dst = "127.0.0.1";
                int pto = 2000;
                p.setData(b); //Cambia los datos
                
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