/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica01;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Practica01 {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        String host = "192.168.43.244";

        socket = new Socket(host, 5555);

        File file = new File("C:\\Users\\iAngelMx\\Documents\\GitHub\\filezilla.exe");
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[32 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        int a = 0;
        while ((count = in.read(bytes)) > 0) {
            a++;
            out.write(bytes, 0, count);
            System.out.println("Cuenta Cli:"+count+" lin: "+a);
        }

        out.close();
        in.close();
        socket.close();
    }
}