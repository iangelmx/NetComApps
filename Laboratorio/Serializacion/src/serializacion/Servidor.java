package serializacion;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import serializacion.Persona;

public class Servidor {
    
    public static void iniciaServidor(int puerto, int buffer) throws IOException{
        ServerSocket serverSocket = null;
        Persona per1=null;
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
        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("No se pudo aceptar conexión: "+ex);
        }
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
        iniciaServidor(3060, 20);
    }
}
