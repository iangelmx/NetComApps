package hilos;

public class HilosThread {

    public static void main(String[] args) {
        
        long now = System.currentTimeMillis();
        AlfabetoThread hilo1 = new AlfabetoThread(1, now);
        NumerosThread hilo2 = new NumerosThread(2, now);
        
        hilo1.start();
        hilo2.start();
        
    }
    
    
    
}
