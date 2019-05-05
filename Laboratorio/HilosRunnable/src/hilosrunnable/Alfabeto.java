package hilosrunnable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Alfabeto {
    private String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private int idProcess;
    private long timestamp;
    
    public Alfabeto(int numHilo, long timestamp){
        this.idProcess = numHilo;
        this.timestamp = timestamp;
    }
    
    public void imprimeAlfabeto(){
        for (char letra : this.abecedario.toCharArray()) {
            System.out.println( "H"+this.idProcess+" (LETRAS): "+ letra + " en: "+ ((System.currentTimeMillis()-this.timestamp)/1000) +" segs.");
            this.esperaPor(70);
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
