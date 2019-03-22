package serializacion;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import serializacion.Persona;

public class servidor {
    
        
    public static void run_servidor() throws IOException{
    ServerSocket serverSocket = null;
    Persona per1=null;
    Gson gson=new Gson();
        try {
            serverSocket = new ServerSocket(3060);
            System.out.println("servidor corriendo");
        } catch (IOException ex) {
            System.out.println("no se puede correr el servidor en este puertos");
        }
        
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
        
        try {
            
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("no se puede aceptar la conexión del clientes");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("No se puede obtener el socket de entrada");
        }
   
            try {
            out = new FileOutputStream("src\\files\\recibido.json");
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
            FileInputStream fstream = new FileInputStream("src\\uploads\\recibido.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String line;
                while ((line = br.readLine()) != null) {
                    per1= gson.fromJson(line,persona.class);
                   System.out.println(line);
                   System.out.println(per1.toString());
                }
               
		
            serverSocket.close();
       
    
    }
    public static void main(String[] args) throws IOException {
        
        run_servidor();
    }
}
