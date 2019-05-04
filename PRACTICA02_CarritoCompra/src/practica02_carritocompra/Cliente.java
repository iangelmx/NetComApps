package practica02_carritocompra;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import practica02_carritocompra.Item;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class Cliente extends javax.swing.JFrame {
    Item [] clonProd = null;
    Item [] items = null;
    
    Socket socket = null;
    String hostToConnect = "";
   
    
    public static File writeToFile(String pathFile, String toWrite) throws IOException{
        File file=new File( pathFile );
        FileWriter fw = new FileWriter(file);
        PrintWriter escribir = null;
        escribir = new PrintWriter(fw);
        escribir.write(toWrite);
        escribir.close();
        return file;
    }
    
    private void addTableHeader(PdfPTable table) {
        //headers.add("SKU \t|\t Producto \t|\t Cantidad \t|\t Precio");
        Stream.of("SKU", "Producto", "Cantidad", "Precio Unit", "Subtotal")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }
    private void addRows(PdfPTable table, String[] renglon) {
        table.addCell(renglon[0]);
        table.addCell(renglon[1]);
        table.addCell(renglon[2]);
        table.addCell(renglon[3]);
        table.addCell(renglon[4]);
    }
    
    public void connectToServer(String host, int puerto, int buffer) throws IOException{
        
        InputStream in = null;
        OutputStream out = null;
        socket = new Socket(host, puerto);
        in = socket.getInputStream();
 
        out = new FileOutputStream("src\\inventario\\inventarioFromS_ToC.json");

        byte[] bytes = new byte[buffer*1024];
        int count;
        int i=0;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
            i++;
            System.out.println("linea "+i+". Cuenta de recepción: "+count);
        }
        
        System.out.println("Recibió el archivo.");
        
        FileInputStream fstream = new FileInputStream("src\\inventario\\inventarioFromS_ToC.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        
        int a=0;
        Gson gson = new Gson();
        while ((line = br.readLine()) != null) {
           System.out.println("Lee linea: "+line);
           items = gson.fromJson(line,Item[].class);
           System.out.println(line);
           a+=1;
        }
        
        
        
        System.out.println("");
        for( Item producto : items ){
            //System.out.println(producto.nombre);
            modelo.addRow(new Object[] {producto.sku, producto.nombre, producto.exis});
            //modelo.addRow(new Object[]{"1AF3F30", "Carrito", 5});
        }
        
        
        
        clonProd = items;
        //System.out.println("Antes de for"+items[0].toString());
        for(int b=0; b < clonProd.length; b++){
            clonProd[b].exis = 0;
            /*System.out.println("clon inicial"+clonProd[b].toString());
            System.out.println("items inicial"+items[b].toString());*/
        }
        //System.out.println("Después de for"+items[0].toString());
        
        socket.close();
        
    }
    
    DefaultTableModel modelo = null;
    DefaultTableModel carritoTable = null;
    public Cliente() {
        initComponents();
        
        modelo = (DefaultTableModel) tablaDatos.getModel();
        carritoTable = (DefaultTableModel) carrito.getModel();
        try{
            hostToConnect = JOptionPane.showInputDialog("¿A dónde te quieres conectar?");
            connectToServer(hostToConnect, 3060, 300);
        }
        catch(IOException ex){
            System.out.println("Excepción: "+ex);
        }
        
        
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        carrito = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRACTICA 02 Carrito de Compra");

        jLabel2.setText("Se hará la conexión y se mostrarán las existencias aquí abajo:");

        tablaDatos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "Nombre", "Existencias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaDatos);

        jButton1.setText("Agregar a carrito");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Comprar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        carrito.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        carrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "Nombre", "Existencias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(carrito);

        jButton3.setText("Restar de carrito");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)
                            .addGap(108, 108, 108)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        /* De los items sumar o restar */
        int rowSelected = tablaDatos.getSelectedRow();
        clonProd[ rowSelected ].exis++;
        
        /*System.out.println("Add items: "+items[rowSelected].toString() );
        System.out.println("Add clon: "+clonProd[rowSelected].toString() );*/

        carritoTable.setRowCount(0);
        
        for( Item producto : clonProd ){
            //System.out.println(producto.toString());
            if(producto.exis > 0){
                carritoTable.addRow(new Object[] {producto.sku, producto.nombre, producto.exis});
            }
        }
        
        
        //carritoTable.addRow(new Object[] {clonProd[rowSelected].sku, clonProd[rowSelected].nombre, clonProd[rowSelected].exis});
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int rowSelected = carrito.getSelectedRow();
        String skuToDel = carritoTable.getValueAt(rowSelected, 0).toString();
        for(int a = 0; a<clonProd.length; a++){
            if( clonProd[a].sku.equals(skuToDel) && clonProd[ a ].exis >= 1){
                clonProd[ a ].exis--;
            }
        }
        
        carritoTable.setRowCount(0);
        
        for( Item producto : clonProd ){
            System.out.println(producto.toString());
            if(producto.exis > 0){
                carritoTable.addRow(new Object[] {producto.sku, producto.nombre, producto.exis});
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean flag = true;
        try {
            socket= new Socket(hostToConnect,3060);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream in = null;
        OutputStream out = null;
        try{
            FileInputStream fstream = new FileInputStream("src\\inventario\\inventarioFromS_ToC.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line;
            
            Item [] original = null;
            Gson gson = new Gson();
            while ((line = br.readLine()) != null) {
               //System.out.println("Lee linea: "+line);
               original = gson.fromJson(line,Item[].class);
               System.out.println(line);
            }
            
            for(int a=0; a<clonProd.length; a++){
                if( original[a].exis < clonProd[a].exis ){
                    JOptionPane.showMessageDialog( null, "Ooops!\nNo hay suficiente en inventario para: "+String.valueOf(clonProd[a].exis)+" "+clonProd[a].nombre, "Error de compra", JOptionPane.ERROR_MESSAGE);
                    flag = false;
                }
            }
            
        }catch(Exception ex){
            System.out.println("Excepcion 01: "+ex);
        }
        // TODO add your handling code here:
        
        for(int a=0; a<clonProd.length; a++){
            /*
            System.out.println("items: "+items[a].toString());
            System.out.println("clon: "+clonProd[a].toString());*/
            if( items[a].exis < clonProd[a].exis ){
                JOptionPane.showMessageDialog( null, "Ooops!\nNo hay suficiente en inventario para: "+String.valueOf(clonProd[a].exis)+" "+clonProd[a].nombre, "Error de compra", JOptionPane.ERROR_MESSAGE);
                flag = false;
            }
        }
        
        int count=0;
        
        if(flag == true){
            
            Document document = new Document();
            try{
                PdfWriter.getInstance(document, new FileOutputStream("src\\salida.pdf"));

                document.open();
                Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 24, BaseColor.BLACK);
                Chunk chunk = new Chunk("Ticket de compra", font);
                
                document.add(chunk);
                
                Font font2 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
                String salida = "";
                
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String fecha = dateFormat.format(date);
                
                PdfPTable table = new PdfPTable(5);
                addTableHeader(table);
                
                /*Paragraph headers = new Paragraph();
                headers.add("SKU \t|\t Producto \t|\t Cantidad \t|\t Precio");
                headers.setFont(font2);*/
                
                document.add(new Paragraph(""));
                document.add(new Paragraph("Fecha transacción: "+ fecha+"\n\n"));
                document.add(new Paragraph(""));
                //document.add( headers );
                
                
                
                double total = 0;
                for(int a=0; a<clonProd.length; a++){
                    
                    if( clonProd[a].exis > 0 ){
                        //chunk2.append(clonProd[a].sku+" | "+clonProd[a].nombre+ " | "+String.valueOf(clonProd[a].exis)+"\n");
                        /*salida+=clonProd[a].sku+"\t|\t"+clonProd[a].nombre+ "\t|\t"+String.valueOf(clonProd[a].exis)+"\t|\t"+String.valueOf(clonProd[a].precio)+"\n";*/
                        addRows(table, new String[]{clonProd[a].sku, clonProd[a].nombre, String.valueOf(clonProd[a].exis),"$"+String.valueOf(clonProd[a].precio), "$"+String.valueOf((clonProd[a].precio)*clonProd[a].exis) } );
                        total += (clonProd[a].precio)*clonProd[a].exis;
                    }
                }
                
                addRows(table, new String[]{"-","-","-","-","-"} );
                addRows(table, new String[]{"*","*","*","Total a pagar", "$"+String.valueOf(total)} );
                
                
                
                document.add(table);
                //salida += "\nTotal a pagar: "+;
                
                
                

                document.add( new Paragraph(salida) );
                document.close();
            }
            catch(Exception ex){
                System.out.println("Exception PDF:"+ex.toString());
            }
            
            Gson gson = new Gson();
            String jsonShipping = gson.toJson(clonProd);
            
            try {
                File archivo = new File("src\\inventario\\shippingList.json");
                long length = archivo.length();
                byte[] bytes = new byte[200 * 1024];
                out= new FileOutputStream(archivo);
                out.write(jsonShipping.getBytes());
                
                in = new FileInputStream(archivo);
                out = socket.getOutputStream();
                System.out.println("Escribiendo en socket -cliente");
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
              
                JOptionPane.showMessageDialog( null, "Compra exitosa!!\nSu ticket está en: src/salida.pdf", "Compra finalizada", JOptionPane.INFORMATION_MESSAGE);
                //System.exit(0);
                //out.flush();
                socket.close();
            }catch (Exception ex) {
                System.out.println("Excepción encontrada!: "+ex);
            }
        }
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable carrito;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
