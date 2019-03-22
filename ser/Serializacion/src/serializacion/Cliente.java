package serializacion;

import serializacion.Persona;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;

public class Cliente {
    
    public static File writeToFile(String pathFile, String toWrite) throws IOException{
        File file=new File( pathFile );
        FileWriter fw = new FileWriter(file);
        PrintWriter escribir = null;
        escribir = new PrintWriter(fw);
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
        
        File file = writeToFile("src\\objetos\\serialized.json", datos);
        
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
    
    public static void main(String[] args) throws IOException {
         Persona personita= new Persona(
                "Angel Ramírez",
                "RAAA960323HDFMLN06",
                "1996-03-23",
                'M',
                21,
                1.76
        );
        
        Gson gson= new Gson();
        String serializado = gson.toJson(personita);
        
        System.out.println(personita.toString());
        System.out.println(serializado);

        sendFileToServer(serializado, "localhost", 3060, 10);
    }
    
    
}
