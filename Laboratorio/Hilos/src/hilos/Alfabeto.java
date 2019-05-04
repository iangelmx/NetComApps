package hilos;

public class Alfabeto extends Thread{
    
    private String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private int idHilo;
    private long timestamp;
    
    public Alfabeto(int numHilo, long timestamp){
        this.idHilo = numHilo;
        this.timestamp = timestamp;
    }
    
    @Override
    public void run(){
        for (char letra : this.abecedario.toCharArray()) {
            System.out.println( "H"+this.idHilo+" (LETRAS): "+ letra + " en: "+ ((System.currentTimeMillis()-this.timestamp)/1000) +" segs.");
            this.esperaPor(5);
        }
    }
    
    private void esperaPor(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
