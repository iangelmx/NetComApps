package practica01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out = new FileOutputStream("C:\\Users\\neyer\\Documents\\ESCOM\\8vo Semestre\\Aplicaciones para Comunicaciones en Red\\P1\\out1.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        
        Socket socket2 = null;
        InputStream in2 = null;
        OutputStream out2 = null;
        
        try {
            socket2 = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }
        
        try {
            in2 = socket2.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out2 = new FileOutputStream("C:\\Users\\neyer\\Documents\\ESCOM\\8vo Semestre\\Aplicaciones para Comunicaciones en Red\\P1\\out2.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes1 = new byte[16*1024];

        int count1;
        while ((count1 = in2.read(bytes1)) > 0) {
            out2.write(bytes1, 0, count1);
        }

        
        
        socket2.close();
        serverSocket.close();
    }
}