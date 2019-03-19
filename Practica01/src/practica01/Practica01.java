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
        String host = "10.100.73.11";

        socket = new Socket(host, 5555);

        File file1 = new File("C:\\Users\\iAngelMx\\Desktop\\VALLARTA\\archivo.txt");
        
        File file2 = new File("C:\\Users\\iAngelMx\\Desktop\\VALLARTA\\fff.txt");
        // Get the size of the file
        long length = file1.length();
        byte[] bytes = new byte[32 * 1024];
        InputStream in = new FileInputStream(file1);
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
        InputStream in2 = new FileInputStream(file2);
        OutputStream out2 = socket.getOutputStream();
        int count2;
        int a2 = 0;
        while ((count2 = in.read(bytes)) > 0) {
            a++;
            out.write(bytes, 0, count2);
            System.out.println("Cuenta Cli:"+count2+" lin: "+a2);
        }

        out2.close();
        in2.close();
        socket.close();
    }
}