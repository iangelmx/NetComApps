package hilos;

public class Hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long now = System.currentTimeMillis();
        Alfabeto hilo1 = new Alfabeto(1, now);
        CuentaNumeros hilo2 = new CuentaNumeros(2, now);
        
        hilo1.start();
        hilo2.start();
        
    }
    
    
    
}
