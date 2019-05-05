package hilosrunnable;

public class Numeros {
    
    private int idProcess;
    private long timestamp;
    public Numeros(int numHilo, long timestamp){
        this.idProcess = numHilo;
        this.timestamp = timestamp;
    }
    
    public void imprimeNumeros(){
        for(int a=1; a<=100; a++){
            System.out.println( "H"+this.idProcess+" (Nums): "+ a + " en: "+ ((System.currentTimeMillis()-this.timestamp)/1000) +" segs.");
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
