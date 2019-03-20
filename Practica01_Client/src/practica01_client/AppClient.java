/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica01_client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author iAngelMx
 */
public class AppClient extends javax.swing.JFrame {

    /**
     * Creates new form AppClient
     */
    public AppClient() {
        initComponents();
        btnEnviar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pathFile = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        buffer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        progreso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Práctica 01 - Envía archivos con Sockets TCP");

        jLabel2.setText("Selecciona el archivo que deseas enviar...");

        jButton1.setText("Selecciona archivo");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        btnEnviar.setText("Enviar ! :D");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        pathFile.setColumns(20);
        pathFile.setRows(5);
        jScrollPane1.setViewportView(pathFile);

        jLabel3.setText("Tamaño de buffer:");

        buffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bufferActionPerformed(evt);
            }
        });

        jLabel4.setText("Progreso:");

        progreso.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buffer)
                                    .addComponent(jLabel4)
                                    .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buffer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void enviaInfo(String path, int tamBuffer) throws IOException{
        int var = 0;
        if (tamBuffer == 0){
            var = 16;
        }else{
            var = tamBuffer;
        }
        Socket socket = null;

        String host = "192.168.0.9";


        socket = new Socket(host, 5555);

        File file = new File(path);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[var * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        int a = 0;
        double progresoEnvio = 0;
        long acum = 0;
        while ((count = in.read(bytes)) > 0) {
            a++;
            acum += count;
            out.write(bytes, 0, count);
            System.out.println("Cuenta Cli:"+count+" lin: "+a);
            progresoEnvio = (100 * acum) / length;
            progreso.setText(String.valueOf(progresoEnvio));
        }
        out.close();
        in.close();
    }
    
    File[] files;
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        //Creamos el objeto JFileChooser
        JFileChooser chooser=new JFileChooser();

        //Indicamos lo que podemos seleccionar
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //Creamos el filtro
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");

        //Le indicamos el filtro
        //chooser.setFileFilter(filtro);
        
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(null);

        files = chooser.getSelectedFiles();
        
        String str = "";
        int nArchivos = files.length;
        
        String detalles = "";
        for( File archivo : files ){
            
            String name = archivo.getName();
            String ext = name.substring(name.lastIndexOf("."));
            String tam = String.valueOf(archivo.length());
            
            str+= "Archivo: "+name+"\n";
            str+= "Extens.: "+ext+"\n";
            str+= "Tamanio: "+tam+" bytes\n";
            str+= "----------------\n";
            detalles += name+"|"+ext+"|"+tam+"\n";
        }

        
        String ruta = "src\\uploads\\archivo.txt";

        btnEnviar.setEnabled(true);
        
        File archivo = new File(ruta);
        try{
            FileWriter fw = new FileWriter(archivo);
        
            PrintWriter escribir;
            if(archivo.exists()) {
                escribir = new PrintWriter(fw);
                escribir.write(detalles);
            } else {
                escribir = new PrintWriter(fw);
                escribir.write(detalles);
            }
            escribir.close();
            enviaInfo("src\\uploads\\archivo.txt",0);

          }
        catch(Exception ex){
        System.out.println(ex);}
        
        
        
        pathFile.setText(str);
    }//GEN-LAST:event_jButton1MouseClicked

    private void bufferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bufferActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bufferActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        
        int tamBuf = Integer.parseInt(buffer.getText());
        String str = "", detalles="";
        
        for( File archivo : files ){
            String path = archivo.getPath();
            try{
                enviaInfo(path, tamBuf);
            }
            catch(Exception ex){
            System.out.println(ex);}
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JTextField buffer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea pathFile;
    private javax.swing.JLabel progreso;
    // End of variables declaration//GEN-END:variables
}
